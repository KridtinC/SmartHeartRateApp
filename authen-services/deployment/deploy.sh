#!/usr/bin/env bash
#
# DESCRIPTION: ECS Deployment Script for SmartHeartRate services
# MAINTAINER: Kridtin Chwalratikool (Credit to Justin Kulesza)
# DEPENDENCIES: bash (>= 4.4.12), python (~> 2.7.13), awscli (~> 1.11.91), docker-ce (>= 17.03.1)
#

set -e

# BEGIN DEFINITIONS OF VARIABLES #
ECS_REGION='ap-southeast-1'
ECS_CLUSTER_NAME='cloud2019-smart-heartrate'
ECS_SERVICE_NAME='smart-heartrate-authen-services'
ECS_TASK_DEFINITION_NAME='smart-heartrate-authen-task'
ECR_NAME='smart-heartrate-authen-repo'

AWS_ID=''
VERSION=$(date +%s)
AWSCLI_VER_TAR=1.11.91
# END DEFINITIONS OF VARIABLES #

# BEGIN OTHER VAR DEFINITIONS #
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
ORIGINAL_BRANCH="$(git rev-parse --abbrev-ref HEAD)"
ENVIRONMENT="test"
BRANCH=""
AWSCLI_VER=$(aws --version 2>&1 | cut -d ' ' -f 1 | cut -d '/' -f 2)
# END OTHER VAR DEFINITIONS #

if [[ ${AWSCLI_VER} < ${AWSCLI_VER_TAR} ]]
then echo "ERROR: Please upgrade your AWS CLI to version ${AWSCLI_VER_TAR} or later!"
  exit 1
fi

usage() {
  echo "Usage: $0 -i <id> [-b <branch>]"
  echo " <id> must be your AWS ID"
  echo " <branch> must be a valid ref. If no branch is provided, you will be prompted for one."
  exit 1
}

while getopts ":b:i:h" o; do
    case "${o}" in
        b)
            BRANCH=${OPTARG}
            git rev-parse --abbrev-ref "${BRANCH}" || usage
            ;;
        i)
            AWS_ID=${OPTARG}
            ECR_URI="${AWS_ID}".dkr.ecr.ap-southeast-1.amazonaws.com || usage
            ;;
        *)
            usage
            ;;
    esac
done
shift $((OPTIND-1))

if [[ -z "${BRANCH}" ]] ; then
  echo -n "Which branch to deploy from [$(git rev-parse --abbrev-ref HEAD)] ? "
  read -r line
  if [[ -z "${line}" ]]; then
    BRANCH="$(git rev-parse --abbrev-ref HEAD)"
  else
    git rev-parse --abbrev-ref "${line}" || exit 1
    BRANCH="${line}"
  fi
fi

echo "You are deploying ${BRANCH} to ${ENVIRONMENT}."

# Docker/ECR operations
(
  cd "${DIR}/.."

  docker build --pull -t "${ECR_NAME}:latest" -f ./Dockerfile .
  docker tag "${ECR_NAME}:latest" "${ECR_URI}/${ECR_NAME}:${VERSION}"
  aws ecr get-login-password --region "${ECS_REGION}"
  aws ecr create-repository --repository-name ${ECR_NAME}
  docker push "${ECR_URI}/${ECR_NAME}:${VERSION}"

)

# ECS operations
# (
#   cd "${DIR}/.."
#   # Create the new ECS container definition from the last task definition
#   # CONTAINER_DEF=$(cat ./deployment/container_definition.json)
#   # echo ${CONTAINER_DEF}
#   # aws ecs register-task-definition --region "${ECS_REGION}" --family "${ECS_TASK_DEFINITION_NAME}-${ENVIRONMENT}" --container-definitions "${CONTAINER_DEF}" --memory 1024 --cpu 256
#   # aws ecs create-cluster --cluster-name "${ECS_CLUSTER_NAME}"
#   # aws ecs create-service \
#   #   --cluster "${ECS_CLUSTER_NAME}" \
#   #   --service-name ${ECS_SERVICE_NAME} \
#   #   --task-definition "${ECS_TASK_DEFINITION_NAME}-${ENVIRONMENT}":5 \
#   #   --desired-count 1
#   # aws ecs update-service --region "${ECS_REGION}" --cluster "${ECS_CLUSTER_NAME}" --service "${ECS_SERVICE_NAME}-${ENVIRONMENT}"  --task-definition "${ECS_TASK_DEFINITION_NAME}-${ENVIRONMENT}"
#  )
