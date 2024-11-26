package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var ipUnit by remember { mutableStateOf("SELECT") }
    var opUnit by remember { mutableStateOf("SELECT") }
    var ipExpanded by remember { mutableStateOf(false) }
    var opExpanded by remember { mutableStateOf(false) }
    var submitCheck by remember { mutableStateOf(false) }
    var iConversion by remember { mutableDoubleStateOf(1.0) }
    var oConversion by remember { mutableDoubleStateOf(1.0) }
    val context = LocalContext.current


    fun calculation() {
        val ipToDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (ipToDouble * iConversion)/oConversion
        outputValue = result.toString()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(6.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally
//      modifier = Modifier.padding(16.dp), //only one argument for modifier can exist
    ) {
        Text("UNIT CONVERTOR", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            inputValue,
            { inputValue = it },
            label = { Text("Enter a value") }
        )

//        Modifier.padding() makes space all around the object
        Spacer(Modifier.height(15.dp)) //only gives space after OutlineTExtField

        Row {
            Box {
                Button({ ipExpanded = true }) {
                    Text(ipUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(ipExpanded, { ipExpanded = false }) {
                    DropdownMenuItem({ Text("cm") },
                        {
                            ipUnit = "cm"
                            iConversion = 1.0
                            ipExpanded = false
                            submitCheck = false
                        })
                    DropdownMenuItem({ Text("m") },
                        {
                            ipUnit = "m"
                            iConversion = 100.0
                            ipExpanded = false
                            submitCheck = false
                        })
                    DropdownMenuItem({ Text("km") },
                        {
                            ipUnit = "km"
                            iConversion = 100000.0
                            ipExpanded = false
                            submitCheck = false
                        })
                }
            }

                Spacer(Modifier.width(40.dp))

                Box {
                    Spacer(Modifier.height(15.dp))
                    Button({ opExpanded = true }) {
                        Text(opUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    }
                    DropdownMenu(opExpanded, { opExpanded = false }) {
                        DropdownMenuItem({ Text("cm") },
                            {
                                opUnit = "cm"
                                oConversion = 1.0
                                opExpanded = false
                                submitCheck = false
                            })
                        DropdownMenuItem({ Text("m") },
                            {
                                opUnit = "m"
                                oConversion = 100.0
                                opExpanded = false
                                submitCheck = false
                            })
                        DropdownMenuItem({ Text("km") },
                            {
                                opUnit = "km"
                                oConversion = 100000.0
                                opExpanded = false
                                submitCheck = false
                            })
                    }
                }
            }

        Button(onClick = {
            if (ipUnit == "SELECT" || opUnit == "SELECT") {
                Toast.makeText(context, "Please select units", Toast.LENGTH_SHORT).show()
            } else {
                submitCheck = true
                calculation()
            }
        },
            Modifier.padding(26.dp)) {
            Text("CONVERT")
        }
        if (submitCheck) {
            Text(
                "Result: $outputValue $opUnit",
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Monospace,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UCPreview() {
    UnitConverter()
}