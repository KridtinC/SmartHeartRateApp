import retrofit2.Retrofit

class MonitoringRepository{
    private var hostname:String = "localhost"
    val PORT:String = "3000"
    private lateinit var url:String
    private val retrofit: Retrofit= Retrofit.Builder()
        .baseUrl(getUrl())
        .build()

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