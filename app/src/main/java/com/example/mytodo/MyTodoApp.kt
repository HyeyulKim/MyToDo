package com.example.mytodo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//Application 클래스
//HiltApplication으로 초기화
@HiltAndroidApp
class MyTodoApp : Application() {
    // 앱 전역 초기화가 필요하면 여기에 작성 가능
    // Crashlytics, Firebase 등 초기화 코드도 여기서 넣을 수 있음.
}
