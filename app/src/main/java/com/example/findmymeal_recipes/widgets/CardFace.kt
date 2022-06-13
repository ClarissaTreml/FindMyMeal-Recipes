package com.example.findmymeal_recipes.widgets

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

//@credits: https://fvilarino.medium.com/creating-a-rotating-card-in-jetpack-compose-ba94c7dd76fb
enum class CardFace(val angle: Float) {

    Front(0f) { //angle
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

enum class RotationAxis {
    AxisX,
    AxisY,
}

@ExperimentalMaterialApi
@Composable
fun FlipCard(
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier,
    axis: RotationAxis = RotationAxis.AxisY,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {
    //https://developer.android.com/jetpack/compose/animation
    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        )
    )
    Card(
        onClick = { onClick(cardFace) },
        modifier = modifier
            .height(200.dp)
            .width(450.dp)
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
            .graphicsLayer {
                if (axis == RotationAxis.AxisX) {
                    rotationX = rotation.value
                } else {
                    rotationY = rotation.value
                }
                cameraDistance = 12f * density //Rotationsdichte (Slap)
            },
    ) {
        if (rotation.value <= 90f) {
            Box(
                Modifier//.width(250.dp).height(300.dp)
            ) {
                front()
            }
        } else {
            Box(
                Modifier
                    //.width(250.dp).height(300.dp)
                    .graphicsLayer {
                        if (axis == RotationAxis.AxisX) {
                            rotationX = 180f //Ansonsten Spiegelverkehrt
                        } else {
                            rotationY = 180f
                        }
                    },
            ) {
                back()
            }
        }
    }
}