package com.mibalance.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mibalance.app.R

@Composable
fun LogoMiBalance() {

    val verde = MaterialTheme.colorScheme.primary
    val verdeOscuro = MaterialTheme.colorScheme.tertiary
    val menta = MaterialTheme.colorScheme.secondary
    val blanco = MaterialTheme.colorScheme.surface

    Box(
        modifier = Modifier
            .size(160.dp)
            .clip(CircleShape)
            .background(blanco),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(105.dp)) {

            // Cuerpo de la cartera
            drawRoundRect(
                color = verde,
                topLeft = Offset(
                    x = size.width * 0.10f,
                    y = size.height * 0.30f
                ),
                size = Size(
                    width = size.width * 0.80f,
                    height = size.height * 0.55f
                ),
                cornerRadius = CornerRadius(25f)
            )

            // Parte superior
            drawRoundRect(
                color = verdeOscuro,
                topLeft = Offset(
                    x = size.width * 0.25f,
                    y = size.height * 0.20f
                ),
                size = Size(
                    width = size.width * 0.55f,
                    height = size.height * 0.20f
                ),
                cornerRadius = CornerRadius(18f)
            )

            // Broche
            drawCircle(
                color = menta,
                radius = size.width * 0.09f,
                center = Offset(
                    x = size.width * 0.77f,
                    y = size.height * 0.57f
                )
            )
        }
    }
}

@Composable
fun Bienvenida(onComenzar: () -> Unit) {

    val colores = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colores.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // LOGOTIPO Y NOMBRE
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colores.secondary)
                .padding(vertical = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LogoMiBalance()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.nombre_app),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = colores.tertiary
            )

            Text(
                text = stringResource(R.string.eslogan),
                fontSize = 15.sp,
                color = colores.onSecondary
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = stringResource(R.string.beneficio_uno),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = colores.onBackground
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = stringResource(R.string.beneficio_dos),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = colores.onBackground
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = stringResource(R.string.beneficio_tres),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = colores.onBackground
        )

        Spacer(modifier = Modifier.height(65.dp))

        // BOTÓN COMENZAR
        Button(
            onClick = onComenzar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colores.primary
            )
        ) {
            Text(
                text = stringResource(R.string.boton_comenzar),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colores.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = stringResource(R.string.mensaje_prototipo),
            fontSize = 11.sp,
            color = colores.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}