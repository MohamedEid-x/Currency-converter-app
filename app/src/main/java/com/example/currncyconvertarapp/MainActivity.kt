package com.example.currncyconvertarapp
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {
    //val TAG= "MainActivity"

    private val AED = "AED"
    private val GBP = "GBP"
    private val Euro = "Euro"
    private val British_Pound = "British Pound"
    private val Indian_Rupee = "Indian Rupee"
    private val egyptionbound = "Egyptian Pound"
    private val americandollar = "American Dollar"
// strings created to use it in list in the next step


    val values = mapOf(
        egyptionbound to 19.68,
        americandollar to 1.0,
        AED to 3.67,
        GBP to 0.91,
        Euro to 1.03,
        British_Pound to 0.90,
        Indian_Rupee to 82.3
    )

    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        pouplateDropDownmenu()

        AmountEditText.addTextChangedListener {
            CalculateResult()
        }
        toMenu.setOnItemClickListener { parent, view, position, id ->
            CalculateResult()
        }
        FromMenu.setOnItemClickListener { parent, view, position, id ->
            CalculateResult()

        }
        Toolbar.inflateMenu(R.menu.optionsmenu)  // first step using inflateMenu method to inflate the menu

        Toolbar.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {

                R.id.share_action -> {
                    val message= "${AmountEditText.text.toString()} ${ FromMenu.text.toString()} is equal ${ShowResult.text.toString()} ${toMenu.text.toString()}"
                    val ShareIntent = Intent(Intent.ACTION_SEND)
                    ShareIntent.type="Text/plain"
                    ShareIntent.putExtra(Intent.EXTRA_TEXT,message)
                    if(ShareIntent.resolveActivity(packageManager) != null) {
                        startActivity(ShareIntent)
                    }else {
                    }                }
                R.id.help_actoin -> { Toast.makeText(this, "help ", Toast.LENGTH_SHORT).show()}
                R.id.setting_action->{ Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show()}
                R.id.phone_contact->{ Toast.makeText(this, "phone", Toast.LENGTH_SHORT).show()}
                R.id.mail_contact->{Toast.makeText(this , "mail", Toast.LENGTH_SHORT).show()}
            }
            true
        }
    }

        private fun CalculateResult() {
            if (AmountEditText.text.toString().isEmpty())
            {
                ShowResult.setError("must add numbers don't let any value Empty")
            }

            if (AmountEditText.text.toString().isNotEmpty()) {
                val amount = AmountEditText.text.toString().toDouble()

                val tovalue = values[toMenu.text.toString()]
                val fromvalue = values[FromMenu.text.toString()]
                val Res = amount.times(tovalue!!.div(fromvalue!!))
                val formmatedtext = String.format("%.2f", Res)
                ShowResult.setText(formmatedtext)
            } else
               AmountEditText.setError("required")


        }

        private fun pouplateDropDownmenu() {
            val listofcountries = listOf(
                egyptionbound,
                AED,
                americandollar,
                Euro,
                British_Pound,
                Indian_Rupee
            ) // create text from strings your created before at the top
            val adapter = ArrayAdapter(this,R.layout.drop_down_list_item, listofcountries) // Arrayadapter(context,layout,list).... adapter used to convert from one string to more than one string
            toMenu.setAdapter(adapter) //use set adapter method to set the adapter on the created adapter
            FromMenu.setAdapter(adapter)
        }

    }


