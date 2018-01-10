package com.github.mag0716.threetenabpsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

/**
 * https://github.com/JakeWharton/ThreeTenABP
 *
 * 参考
 * [Androidで日付時刻を簡単に扱えるThreeTenABPの話](http://techblog.timers-inc.com/entry/2016/06/27/121939)
 * [Java8の日時APIはとりあえずこれだけ覚えとけ](https://qiita.com/tag1216/items/91a471b33f383981bfaa)
 */
class MainActivity : AppCompatActivity() {

    // epoch time -> LocalDateTime
    var date = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 日時の加算・減算
        previousDateButton.setOnClickListener {
            date = date.minusDays(1)
            updateLocalDateTimeTest()
        }
        nextDateButton.setOnClickListener {
            date = date.plusDays(1)
            updateLocalDateTimeTest()
        }

        updateLocalDateTimeTest()
    }

    fun updateLocalDateTimeTest() {
        // フォーマット
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        resultText.text = date.format(formatter)
    }
}
