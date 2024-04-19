package adil.trackerposition.ui


import adil.trackerposition.R
import adil.trackerposition.viewModelConnector.CaptureCamera
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.*


class AddDevice(var user_token:String) : Fragment() {
    lateinit var btnScan:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View = inflater.inflate(R.layout.fragment_add_device, container, false)
        // Inflate the layout for this fragment
        btnScan = view.findViewById(R.id.add_device)
        btnScan.setOnClickListener{
            try {
                scanCode()
            }catch (ex:Exception){
                Toast.makeText(activity, ex.message, Toast.LENGTH_LONG).show()
            }
        }
        try{
            Toast.makeText(activity, user_token, Toast.LENGTH_LONG).show()
            val barcodeEncoder:BarcodeEncoder = BarcodeEncoder()
            val bitmap:Bitmap = barcodeEncoder.encodeBitmap( user_token,BarcodeFormat.QR_CODE, 400, 400)
            val image:ImageView = view.findViewById(R.id.qrcode)
            image.setImageBitmap(bitmap)
        }catch (ex:Exception){
            Toast.makeText(activity, ex.message, Toast.LENGTH_LONG).show()
        }
        return view
    }
    val barLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(ScanContract()) { result: ScanIntentResult ->
        if (result.contents != null) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            builder.setTitle("Result")
            builder.setMessage(result.contents)
            builder.setPositiveButton("OK",
                DialogInterface.OnClickListener {
                                                dialogInterface, i -> dialogInterface.dismiss()
                }).show()
            Toast.makeText(activity, "null", Toast.LENGTH_LONG).show()
        }else{
//            Toast.makeText(activity, result.contents Toast.LENGTH_LONG).show()
        }
    }

    private fun scanCode(){
        val scanOptions: ScanOptions = ScanOptions()
        scanOptions.apply {
            setPrompt("Volume up your flash")
            setBeepEnabled(true)
            setOrientationLocked(true)
            setCaptureActivity(CaptureCamera::class.java)
        }
        barLauncher.launch(scanOptions)

    }
}

