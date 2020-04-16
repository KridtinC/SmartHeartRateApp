import androidx.lifecycle.MutableLiveData
import com.cloudproject.smartheartrate.model.ElderListResponse
import com.cloudproject.smartheartrate.model.MonitorRate
import com.cloudproject.smartheartrate.model.MonitorResponse
import com.cloudproject.smartheartrate.network.AccountServices
import com.cloudproject.smartheartrate.network.MonitorServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MonitoringRepository{
    private var hostname:String = "localhost"
    val PORT:String = "3000"
    private lateinit var url:String
    private val retrofit: Retrofit= Retrofit.Builder()
        .baseUrl(getUrl())
        .build()
    fun getRateList() :MutableLiveData<ArrayList<MonitorRate>>{
        val elderList: MutableLiveData<ArrayList<MonitorRate>> =  MutableLiveData<ArrayList<MonitorRate>>()
        val body:HashMap<String,String> = HashMap()
        body["wtf@gmail.com"] = "123456"
        val req:Call<MonitorResponse>  = retrofit.create(MonitorServices::class.java).getRate(body)
        req.enqueue(object : Callback<MonitorResponse?>{
            override fun onFailure(call: Call<MonitorResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<MonitorResponse?>,
                response: Response<MonitorResponse?>
            ) {
                TODO("Not yet implemented")
            }

        })
        return elderList
    }
    fun getHostname():String{
        return this.hostname
    }
    fun setHostname(inp:String){
        this.hostname = inp
    }
    fun getUrl():String{
        return "http://"+getHostname()+":"+PORT
    }
}