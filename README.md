## MultiPageControl
多布局页面 无侵入布局 为Activity、Fragment、任何View设置等待（loading)、重试(retry)、无数据(empty)页面。



## 效果图

* 不同状态可随意编辑

<img src="imgs/effectpicture.png" />



## 如何使用

将其添加到存储库末尾的根build.gradle中：

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

步骤2.添加依赖项

	dependencies {
	        implementation 'com.github.liulong123:MultiPageControl:1.0.1'
	}




如果多个页面共享加载和重试页面，建议全局设置个基本的。比如在Application中：

```java
public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        //设置默认3个界面
        MultiPageControlManager.defaultLayout(R.layout.base_loading,R.layout.base_retry,R.layout.base_empty);
    }
}
```

在Activity中：

```java
public class MainActivity extends AppCompatActivity
{
    private MultiPageControlManager mMultiPageControlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //为根布局控件
        RelativeLayout relativeLayout = findViewById(R.id.mainRelativeLayout);
        //如在Application中设置了默认控件且不需要使用别的样式可使用此方法创建
        mMultiPageControlManager = MultiPageControlManager.getInstance().init(relativeLayout);
        //优先级高于默认设置的布局  会覆盖默认布局
        mMultiPageControlManager = MultiPageControlManager.getInstance().init(relativeLayout,R.layout.base_loading,R.layout.base_retry,R.layout.base_empty);

        loadData();

    }
}
```

只需要在onCreate中调用`MultiPageControlManager.getInstance().init()`即可。

* 在Fragment中与Activity中用法一致。需注意的是因Fragment生命周期问题建议在 **在onCreateView方法之后执行初始化**

* 可为任何View添加，只需要将对应的View传入第一个参数即可。

点击事件：

```java
public class MainActivity extends AppCompatActivity
{
    private MultiPageControlManager mMultiPageControlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ...
        //初始化完毕之后(点击事件)
        multiPageControlManager.setOnEmptyClickListener(new MultiPageControlManager.OnLoadingAndRetryListener() {
            @Override
            public void onClick() {
                multiPageControlManager.showContent();
            }
        });
        //布局之内子控件点击事件
        multiPageControlManager.setOnRetryChildClickListener(R.id.retryButton, new MultiPageControlManager.OnLoadingAndRetryListener() {
            @Override
            public void onClick() {
                multiPageControlManager.showContent();
            }
        });
        loadData();

    }
}
```


### API

* defaultLayout(int loadingId, int retryId, int emptyId);
* init(View view);
* init(View view, int loadingId, int retryId, int emptyId);
* showContent();
* showRetry();
* showLoading();
* showEmpty();
* setOnRetryClickListener(final OnLoadingAndRetryListener onRetryClickListener);
* setOnEmptyClickListener(final OnLoadingAndRetryListener onEmptyClickListener);
* setOnEmptyChildClickListener(int id, final OnLoadingAndRetryListener onEmptyChildClickListener);
* setOnRetryChildClickListener(int id, final OnLoadingAndRetryListener onRetryChildClickListener);


如有好的建议或者bug请联系 714093365@qq.com