EasyMvp
==========

![License MIT](https://img.shields.io/badge/Apache-2.0-brightgreen)


## 说明
EasyMvp 是为了让多个项目统一代码规范而实现的一个小型框架，只定义了抽象接口， 已经升级到Android X


## Installation

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
         jcenter()
    }
}
```


Add the dependency
```
dependencies {
    implementation 'cc.easyandroid:EasyMvp:1.1.0'
}
```

## How To Use
 
```java
 public class StringActivity extends Activity implements EasyWorkContract.View<String> {
     EasyWorkPresenter<String> presenter = new EasyWorkPresenter<>(new EasyWorkUseCase<String>(new EasyWorkRepository()));
     TextView textView;
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         //...
         textView = findViewById(R.id.text);
         presenter.attachView(this);//绑定view
     }
 
 
     @Override
     public void onStart(Object presenterId) {
         //start
     }
 
 
     public void onCompleted(Object presenterId) {
          //completed
     }
 
     @Override
     public void onError(Object presenterId, Throwable e) {
          //error
     }
 
     @Override
     public void onSuccess(Object tag, String results) {
         deliverResult(tag, results);
         onCompleted(tag);
     }
 
     public void deliverResult(Object presenterId, String results) {
         textView.setText(results);
     }
 
     @Override
     protected void onDestroy() {
         super.onDestroy();
         presenter.detachView();//分离view
     }
 }

```
  
License
-------

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


