package br.unifor.trabalhoandroid27_09_2017

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity(),View.OnClickListener {
    val REQUEST_IMAGE_CAPTURE:Int = 1
    lateinit var mButtonCall:Button
    lateinit var mButtonPhoto:Button
    lateinit var mButtonSearch:Button
    lateinit var mImageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButtonCall = findViewById(R.id.buttonLigar) as Button
        mButtonPhoto = findViewById(R.id.buttonFoto) as Button
        mButtonSearch = findViewById(R.id.buttonPesquisar) as Button
        mImageView = findViewById(R.id.imageViewReturn) as ImageView
        mButtonCall.setOnClickListener(this) //meu override se encarregara do metodo setOnClickListerner
        mButtonSearch.setOnClickListener(this)
        mButtonPhoto.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
            when(view?.id){
                R.id.buttonLigar -> callIntentWithParametrsToCall("997687463")
                R.id.buttonPesquisar -> callIntentWithParametrsToSearch(Constant.frase)
                R.id.buttonFoto -> callIntentWithoutParametrsToTakePhoto()
            }
    }

    private fun callIntentWithoutParametrsToTakePhoto() {
        val it = Intent (MediaStore.ACTION_IMAGE_CAPTURE)
        if (it.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(it, REQUEST_IMAGE_CAPTURE);
        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val extras = data!!.extras
            var imageBitmap:Bitmap = extras.get("data") as Bitmap
            mImageView.setImageBitmap(imageBitmap)

        }

    }

    /*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        mImageView.setImageBitmap(imageBitmap);
    }
}
     */


    private fun callIntentWithParametrsToSearch(query:String) {
        println(null)
        val it = Intent (Intent.ACTION_WEB_SEARCH)
        it.putExtra(SearchManager.QUERY, query);
        if (it.resolveActivity(getPackageManager())!= null){
            startActivity(it)
        }

    }

    private fun callIntentWithParametrsToCall(numbers:String) {
        val it = Intent(Intent.ACTION_DIAL)
        it.setData(Uri.parse("tel:"+numbers))

        if (it.resolveActivity(getPackageManager())!= null){
            startActivity(it)
        }
    }


}
