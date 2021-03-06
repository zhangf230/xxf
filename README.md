# xxf架构
xxf架构是一种MVVM架构,让MVVM更加简洁,规范
1. 去除RxAndroid,用Android自带的lifecycle来管理RxJava的生命周期
2. viewmodel中也可以使用rxjava bind生命周期,跟activity一样
3. 权限可以用RxJava链式调用

#### 用法
##### 引入项目
    implementation 'com.github.axuan2015:xxf:1.0.5'//主要lib

    implementation 'com.android.support:appcompat-v7:28.0.0-rc02'
    implementation 'android.arch.lifecycle:extensions:1.1.1'
    implementation 'com.trello.rxlifecycle2:rxlifecycle:2.2.2'

##### Activity
需要继承XXFactivity
绑定布局 用@BindView
绑定vm  用@BindVM

    @BindView(R.layout.other_activity_devloper_helper_setting)
    @BindVM(DeveloperHelperSettingVM.class)
    public class DeveloperHelperSettingActivity
            extends XXFActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getBinding().setVariable(BR.vm, getVm());
        }
    }

##### Fragment
与Activity类似,继承XXFFragment即可;

##### DialogFaragment
与Activity类似,继承XXFDialogFragment即可;

##### 与RxJava结合
在Activity,Fragment,DialogFragment与ViewModel中都可以使用RxJava管理生命周期,语法保持一致,如下:


      io.reactivex.Observable.interval(1, TimeUnit.SECONDS)
                    .compose(this.<Long>bindUntilEvent(Lifecycle.Event.ON_PAUSE))
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            LogUtils.AndroidD("--------->ex:" + aLong);
                        }
                    });

##### Activity和Fragment生命周期日志

    public class BaseApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            //注册activity和fragment生命周期
            XXFAppLifecycleLogger.register(
                    this,
                    new XXFAppLifecycleLogger.XXFAppLifecycleLoggerBuilder()
                            .setLogCreatedActivity(true)//在activity创建的时候打印Log
                            .setLogActivityIntent(true)//打印Intent参数
                            .setToastCreatedActivity(true)//在activity创建的时候Toast
                            .setLogCreatedFragment(true)//在fragment创建的时候打印Log
                            .setLogFragmentAragments(true)//打印fragment参数
                            .setToastCreatedFragment(true)//在fragment创建的时候Toast
                            .build());

        }
    }

##### 权限使用Rx链式调用

     Disposable subscribe = new XXFPermissions(this)
                    .request(Manifest.permission.CAMERA)
                    .compose(new CameraPermissionTransformer(this,false))
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            ToastUtils.showToast(TestActivity.this, "permission:" + aBoolean);
                        }
                    });

