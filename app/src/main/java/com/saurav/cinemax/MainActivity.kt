package com.saurav.cinemax

import android.animation.Animator
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.lottie.LottieAnimationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<LottieAnimationView>(R.id.lottieSplash)
            .addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) = Unit

                override fun onAnimationEnd(animation: Animator) {
                    findViewById<LottieAnimationView>(R.id.lottieSplash).animate()
                        .alpha(0f)
                        .setDuration(150)
                        .withEndAction {
                            findViewById<LottieAnimationView>(R.id.lottieSplash).visibility =
                                android.view.View.GONE
                        }
                        .start()
                }

                override fun onAnimationCancel(animation: Animator) = Unit

                override fun onAnimationRepeat(animation: Animator) = Unit
            })
    }
}
