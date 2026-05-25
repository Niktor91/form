package com.example.studentform

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentform.ui.theme.*
import java.util.*


@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var nameState     by remember { mutableStateOf("") }
    var surnameState  by remember { mutableStateOf("") }
    var emailState    by remember { mutableStateOf("") }
    var dateState     by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var isAgreed      by remember { mutableStateOf(false) }

    val directionOptions = listOf("Android", "iOS", "Web", "Cyber Security", "AI")
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SwampGreen)
                .padding(top = 40.dp, bottom = 28.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Student\nForm",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                lineHeight = 38.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(2.dp)
                    .background(Color.White.copy(alpha = 0.5f))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "შეავსეთ ყველა ველი სრულყოფილად",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.75f)
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            FlatFormSection(label = "სახელი") {
                FlatInputField(
                    value = nameState,
                    onValueChange = { nameState = it },
                    placeholder = "შეიყვანეთ სახელი",
                    icon = Icons.Filled.Person
                )
            }

            FlatDivider()

            FlatFormSection(label = "გვარი") {
                FlatInputField(
                    value = surnameState,
                    onValueChange = { surnameState = it },
                    placeholder = "შეიყვანეთ გვარი",
                    icon = Icons.Filled.Person
                )
            }

            FlatDivider()

            FlatFormSection(label = "ელ-ფოსტა") {
                FlatInputField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    placeholder = "example@email.com",
                    icon = Icons.Filled.Email
                )
            }

            FlatDivider()

            FlatFormSection(label = "თარიღი") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val cal = Calendar.getInstance()
                            DatePickerDialog(
                                context,
                                { _, year, month, day ->
                                    dateState = "%02d/%02d/%04d".format(day, month + 1, year)
                                },
                                cal.get(Calendar.YEAR),
                                cal.get(Calendar.MONTH),
                                cal.get(Calendar.DAY_OF_MONTH)
                            ).show()
                        }
                ) {
                    OutlinedTextField(
                        value = dateState,
                        onValueChange = {},
                        placeholder = { Text("DD/MM/YYYY", color = TextSecondary, fontSize = 14.sp) },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.CalendarMonth,
                                contentDescription = null,
                                tint = SwampGreen,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false,
                        readOnly = true,
                        shape = RoundedCornerShape(6.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor        = TextPrimary,
                            disabledBorderColor      = BorderColor,
                            disabledLeadingIconColor = SwampGreen,
                            disabledPlaceholderColor = TextSecondary,
                            disabledContainerColor   = CardBackground
                        )
                    )
                }
            }

            FlatDivider()

            FlatFormSection(label = "ფავორიტი მიმართულება") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .border(1.dp, BorderColor, RoundedCornerShape(6.dp))
                        .background(CardBackground)
                ) {
                    directionOptions.forEachIndexed { index, option ->
                        val isSelected = selectedOption == option
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedOption = option }
                                .background(
                                    if (isSelected) SwampGreen.copy(alpha = 0.06f)
                                    else Color.Transparent
                                )
                                .padding(end = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { selectedOption = option },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = SwampGreen,
                                    unselectedColor = BorderColor
                                )
                            )
                            Text(
                                text = option,
                                fontSize = 14.sp,
                                color = if (isSelected) SwampGreen else TextPrimary,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                            )
                        }
                        if (index < directionOptions.lastIndex) {
                            HorizontalDivider(color = BorderColor, thickness = 1.dp)
                        }
                    }
                }
            }

            FlatDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .border(1.dp, if (isAgreed) SwampGreen.copy(alpha = 0.4f) else BorderColor, RoundedCornerShape(6.dp))
                    .background(if (isAgreed) SwampGreen.copy(alpha = 0.04f) else CardBackground)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ვეთანხმები წესებს და პირობებს",
                    fontSize = 14.sp,
                    color = if (isAgreed) SwampGreen else TextPrimary,
                    fontWeight = if (isAgreed) FontWeight.Medium else FontWeight.Normal,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isAgreed,
                    onCheckedChange = { isAgreed = it },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor   = SwampGreen,
                        checkedThumbColor   = Color.White,
                        uncheckedTrackColor = BorderColor,
                        uncheckedThumbColor = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val allFilled = nameState.isNotBlank()
                            && surnameState.isNotBlank()
                            && emailState.isNotBlank()
                            && dateState.isNotBlank()
                    if (!allFilled || selectedOption.isBlank() || !isAgreed) {
                        Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "მონაცემები გაიგზავნა!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SwampGreen),
                shape = RoundedCornerShape(6.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Text(
                    text = "გაგზავნა",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun FlatFormSection(label: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = label.uppercase(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = TextSecondary,
            letterSpacing = 1.2.sp
        )
        content()
    }
}

@Composable
fun FlatInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = TextSecondary, fontSize = 14.sp) },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = null,
                tint = SwampGreen,
                modifier = Modifier.size(20.dp)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(6.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor      = SwampGreen,
            unfocusedBorderColor    = BorderColor,
            focusedTextColor        = TextPrimary,
            unfocusedTextColor      = TextPrimary,
            cursorColor             = SwampGreen,
            focusedContainerColor   = CardBackground,
            unfocusedContainerColor = CardBackground,
            focusedLeadingIconColor   = SwampGreen,
            unfocusedLeadingIconColor = SwampGreen
        )
    )
}

@Composable
fun FlatDivider() {
    HorizontalDivider(
        color = BorderColor,
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}