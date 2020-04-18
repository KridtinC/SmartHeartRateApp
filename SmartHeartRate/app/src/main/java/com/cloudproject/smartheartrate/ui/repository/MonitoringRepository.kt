import android.util.Log
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
    val port:String = "3000"
    private lateinit var url:String
    private val retrofit: Retrofit= Retrofit.Builder()
        .baseUrl(getUrl())
        .build()
    fun getRateList() :MutableLiveData<ArrayList<MonitorRate>>{
        val monitorRateList: MutableLiveData<ArrayList<MonitorRate>> =  MutableLiveData<ArrayList<MonitorRate>>()
        val body:HashMap<String,String> = HashMap()
        body["wtf@gmail.com"] = "123456"
        val req:Call<MonitorResponse>  = retrofit.create(MonitorServices::class.java).getRate(body)
        req.enqueue(object : Callback<MonitorResponse?>{
            override fun onResponse(call: Call<MonitorResponse?>, response: Response<MonitorResponse?>
            ) {
                if(response.isSuccessful){
                    monitorRateList.value = response.body()?.result
                }else {
                    Log.e("MonitorRepository","onResponse Fail: "+response.errorBody())
                }
            }
            override fun onFailure(call: Call<MonitorResponse?>, t: Throwable) {
                monitorRateList.value = null
                Log.e("MonitorListRepo", "onFailure: " + t.message)
            }
        })
        return monitorRateList
    }
    private fun getHostname():String{
        return this.hostname
    }
    fun setHostname(inp:String){
        this.hostname = inp
    }
    fun getUrl():String{
        return "http://"+getHostname()+":"+port
    }
}