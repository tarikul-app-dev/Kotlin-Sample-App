package ayaan.tarikul.kotlinsampleapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReposDataModel (@SerializedName("id")
                           @Expose
                           val id: Int? = null,
                           @SerializedName("node_id")
                           @Expose
                           val nodeId: String? = null,
                           @SerializedName("name")
                           @Expose
                           val name: String? = null,
                           @SerializedName("full_name")
                           @Expose
                           val fullName: String? = null,
                           @SerializedName("private")
                           @Expose
                           val _private: Boolean? = null
)