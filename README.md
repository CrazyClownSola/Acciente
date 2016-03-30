# Acciente
> 个人制作，仿BiliBili，Android版客户端做的练手项目
> 目标是模仿出整个App中所展现的界面效果
> 主要的技术点有以下几个：**Material Design**，**Dagger2**，**RxJava**等，主要目的是熟练运用**Material Design**，和对那套作死的框架的熟练……

-----------------

## 由Dagger2引起的血案
> 与其说是血案，不如说是自己作的一手好死，想要从技术层面实现一套松耦合的框架，逻辑和数据的分离，界面和逻辑的分离，并且熟练运用。
> 估计会与很多人觉得这只是在纸上谈兵，现实是残酷的(其实从某种意义上来说，这样想的才是正常人)
> 
> 那么下面容我整理下这些东西。
> 备注：解释下为什么是Dagger2，而不是Dagger的问题，Dagger2是Google团队新维护的，基于Dagger，并且拓展一些新东西的框架，由于Dagger自身已经不再维护，所以这里采用Dagger2。

### 一些基本概念
> Dagger2：注入式编程的框架代码，提供更方便的单元测试，和模块之间松耦合的实现。

#### Dagger2 当中的一些常用标注
- @Component **组件**用于诠释提供注入的接口(一般都为接口)，由对应的Module实现不同的依赖。编译时会自动产生Dagger+类名的一个编译类。	
- @Module **模块**用于定义在Component中注入的各种类的实例的地方。
- @Inject  **注入**申请注入，注入有个特性，嵌套注入，就是如果你注入的类A，A中已经注入了类B，那么A和B都会注入到A所申请的Component中去。

### 代码拆解

#### presentation , domain 和data
> 其实我估计很多第一看到这套代码的人，可能都会有这样的疑问，这写代码的人是不是脑子有点问题，一开始觉得，是用了二个module做引用，结果发现，这三个合在一起才是一个项目……
> 嘛，的确，我脑子可能真的不怎么正常了。
> 正如调侃所言，这三个module合在一起，才是一个项目。这三个层就如同他们字面解释的意思处理着不同的逻辑，存放着不同的代码。

- **presentation ** 展示层，用来写Activity、Fragment、View的地方，处理界面逻辑。
- **domain** 逻辑层，<font color = #FFFF2626>注意：这一层是纯java代码，没有Android部分！</font>，这是一层专门处理项目业务逻辑的代码部分。如果从架构(这里的架构是指Domain-Driven-Design)的角度看，这是整个项目中最重要也是最需要花精力的部分。如何将业务和代码挂上钩，这往往是项目的难点，但是在楼主的这套代码中，这一层的功能被大幅削弱，毕竟，这只是个练手的项目……没后台，没需求，这部分有点难编！！
- **data** 顾名思义 数据层，这一层存放的代码是用来处理网络请求数据，数据存储和内存操作，这三部分的代码。

##### 三个层级之间的关系：
- **presentation ** 展示层  ---- 从**domain**层获取到数据，然后转换成界面所需的数据集合，然后呈现
- **domain**层在获取数据的时候，会用到**data**层的仓储，具体怎么用到的请参考后续@module的代码，里面会有说明
- **data**层 接受到 **domain**层发来的请求(类似请求)，然后进行网络数据访问。

##### 后言
我曾经尝试过这么件事情，我把presentation拿掉，然后换一个新的presentation层进来，实现了一些界面，调整一下domain 的数据结构，然后……他正常的运行了……



#### ApplicationComponent 和 ApplicationModule
> 这两个是一组，基本上是密不可分的。


*ApplicationComponent.class*
```
@Singleton // 单例
@Component(modules = ApplicationModule.class) // 向该组件注入的对象的实例的实现在ApplicationModule当中。
public interface ApplicationComponent {
    /**
     * 向Application的组件注入自身的方法
     *
     * @param activity 注入方,注意这里传参调整为BaseActivity的原因是为了将Navigator注入组件当中去，这是嵌套注入的一个实现
     */
    void inject(BaseActivity activity);

    /**
     * @return 返回Application的实例
     */
    Context getContext();

    /**
     * @return 返回线程池的实例，如果能习惯使用，这会是个好帮手
     */
    ThreadExecutor getThreadExecutor();

    /**
     * @return 返回UI主线程的实例,这个主要是配合RxJava一起使用的。
     */
    PostExecutionThread getPostExecutionThread();

  
    /**
     * 这里是个很有趣，也非常值得研究的地方。
     * 如果你把这段话注释掉，然后编译，观察以下DaggerApplicationComponent类
     * 然后在把注释放开，再编译，如果你能了解到这两者之间的区别和原因，@Inject这部分基本就能明确了
     *
     * @return 返回仓储
     */
    UserRepository userRepository();
}

```

*ApplicationModule.class*
```
@Module
public class ApplicationModule { // module的方法命名方式多数采用provide+类名的形式，当然这不是强求

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    /**
     * @return 返回Application的实例
     */
    @Provides
    @Singleton
    Context provideApplication() {
        return mApplication;
    }

    /**
     * 向Dagger声明，将JobExecutor的实例绑定到ThreadExecutor的调用方去
     *
     * @param executor 修改指向，将ThreadExecutor的实例绑定为JobExecutor 
     * @return 线程池对象
     */
    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }

    /**
     * 向Dagger声明，将UIThread的实例绑定到PostExecutionThread的调用方上去
     *
     * @param mainThread 改变指向，将PostExecutionThread的指向转变为UIThread
     * @return 返回实例
     */
    @Provides
    @Singleton
    PostExecutionThread provideExecutionThread(UIThread mainThread) {
        return mainThread;
    }

    /**
     * @return 返回Navigator导航的实例
     */
    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }
    
      /**
     * 这是段非常精髓的代码，就这样一句话，就将domain和data层联系了起来，让原本只和domain有联系的presentation层
     * 间接指向了data层，并且进行数据交互。
     *
     * @param userDataRepository data层仓储
     * @return 返回domain的仓储
     */
	@Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

}
```

**关注点**
如果你关心一下代码，编译一下之后你会发现多出这么个类DaggerApplicationComponent，在这个类当中，你会发现，你在Component当中注入的类，在这里会根据Module的规定返回对应的实例。
这是Dagger的魅力，只要你配置正确，你可能完全不用关心实例在哪儿，便能很放心的使用它。






## Material Design
> 这东西么……我还是很推荐各位去好好研究一下，如果你觉得Android源生这条路能走的话……
> 虽然现在RN非常流行，并且大趋势上可能以后源生的代码会更加少。
> 但是…… 我还是觉得研究一下这东西没什么吃亏的。
> 顺便补充一句，这是Google官方提出的设计。


列出个列表，以下这些都是值得去看看的

Gradle build script dependency：
`compile 'com.android.support:design:22.2.0' //可修改版本号为自己匹配`
Design Support Library包含8个控件，具体如下：
| Widget Name	| Description |
| :-- | :-- |
|android.support.design.widget.TextInputLayout	|强大带提示的MD风格的EditText|
|android.support.design.widget.FloatingActionButton	|MD风格的圆形按钮，来自于ImageView|
|android.support.design.widget.Snackbar	|类似Toast，添加了简单的单个Action
|android.support.design.widget.TabLayout|	选项卡
|android.support.design.widget.NavigationView|	DrawerLayout的SlideMenu
|android.support.design.widget.CoordinatorLayout	|超级FrameLayout
|android.support.design.widget.AppBarLayout	|MD风格的滑动Layout
|android.support.design.widget.CollapsingToolbarLayout	|可折叠MD风格ToolbarLayout


## 林散框架之间的配合
> 这一部分主要是对一些框架整合出的代码进行介绍
> 介绍下出场的第三方有那些：
> **Retrofit**、**OkHttp**、**Retrolambda**、**RxJava**
> 这些框架整合在一起可以很漂亮的实现一套网络请求的全过程，有兴趣可以深入研究下


### 网络请求部分
> 网络请求，这件事情，想必各位都有着自己的见解，有的觉得自己写的才是最好的，有的更加信任第三方，我是属于后者，但是不能盲目的去相信任何一个框架，任何一个框架都是有自己的好与不好的一面面，如何取舍，如何判定，这都是需要经过一定量的考量和测试的。
> 经过个人的一些测试，和众多文档的阅读，我选中了**Retrofit2.0**，这里要强调下是**"2.0"**的版本，这是由于2.0的版本对于Retrofit这套框架来说，是一个质的飞跃，一方面是代码简化的，另一方面是性能上的大提升。所以请各位务必注意不要看错版本。
> 补充一句，在Retrofit的官方包里面就自带OKHttp的库包，所以可以只导入单个包就够了。


附上链接[Retrofit](https://github.com/square/retrofit)

废话不多上代码。

ApiConnection.class 工具类，用于创建Retrofit实例
```
/**
 * author: Sola
 * 2015/10/30
 */
public class ApiConnection {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private static OkHttpClient httpClient; //创建OKHttpClient类
    private static Gson gson = new GsonBuilder() //创建Gson解析实例
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

	/** 这个builder可以有很多个，可以根据具体服务的情况进行不同的匹配 **/
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    //添加对于RxJava的适用，致使可以在Api接口当中直接使用Rxjava，这个类是在"com.squareup.retrofit:adapter-rxjava"jar包当中的
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //直接使用Gson转换进行对于参数的适配，这个类是在"com.squareup.retrofit:converter-gson"jar包当中的
                    .addConverterFactory(GsonConverterFactory.create(gson));

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * @param baseUrl 注意这里的基础Url为域名或者Ip地址
     * @param serviceClass 实现服务接口的类
     * @param <S> 返回的服务接口
     * @return 服务接口对象
     */
    public static <S> S createService(String baseUrl, Class<S> serviceClass) {
        if (httpClient == null) { //懒加载模式添加Client对象
            httpClient = new OkHttpClient();
            httpClient.interceptors().add(new LoggingInterceptor());//为OKHttp添加一个日志监听的类
        }
        Retrofit retrofit = builder.baseUrl(baseUrl).client(httpClient).build();//创建Retrofit，并且配置访问的Client
        return retrofit.create(serviceClass);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    /**
     * 监听请求日志用的类
     */
    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.d("OkHttp", String.format("Sending request %s on %s%n%s body [%s]",
                    request.url(), chain.connection(), request.headers(), request.body()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s ",
                    response.request().url(),
                    (t2 - t1) / 1e6d,
                    response.headers()));
            return response;
        }
    }
}

```


BaseService.class 服务接口的实现类，此类可根据具体服务的实现进行调整，这里的例子是根据RestFul形式的接口模版进行配置得到的结果。
```

/**
 * 这个类可以不是个接口，可以是个class，这里很随意，个人比较喜欢interface的形式
 * 
 * author: Sola
 * 2015/11/13
 */
public interface BaseService {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    String BASE_URL = RestConfig.BASE_URL;
    
	String SERVICE_PATH = "service";
	
    String SERVICE_CLASS = "class";
	
    // ===========================================================
    // Methods
    // ===========================================================
	
    /**
     * 登录的接口
     *
     * @param service   对应登录的服务名，由于服务器可能会变，这个参数这里就设定成可变动的
     * @param className 对应登录的服务的具体方法名，同上
     * @param data      接口所需要传入的参数
     * @return 返回接口的响应，并且通过Gson转成对应的参数
     */
	@POST("/{service}/{className}/login")
    Observable<LoginResult> login(@Path("service") String service,
                                  @Path("className") String className,
                                  @Query("paramList") String data);

}


```

调用方式，戏称一句话的请求。
```
 ApiConnection.createService(
                BaseService.BASE_URL, BaseService.class) // 创建Client的过程
                .login(BaseService.SERVICE_PATH,
                        BaseService.SERVICE_CLASS,
                        request.toString())//这里的request可以变成对应服务所需要的传参
                .observeOn(AndroidSchedulers.mainThread())// 这行代码，表示Observable的回调是运行在Android的UI主线程里面。
                .subscribeOn(Schedulers.from(JobExecutor.getInstance()))// 这行代码表示，整个Observable是运行在一个线程池当中。
                .subscribe(resp -> {
	                // 对于返回的结果进行处理，这里已经直接转成对象了，可以直接处理
	                // 并且由于前面已经配置了，UiThread，所以这里可以放心的写界面刷新代码。
                },Throwable::printStackTrace);
```


### RxJava
> 看了上面的代码，我觉得可能会有人困惑Observable是什么东西，在这里我就向各位介绍下**ReactiveX**这套神奇的框架，**RxJava**是**ReactiveX**在Java语言上的延伸代码。同样还有RxJS，RxNodeJs、RxPHP等等，各种语言各种实现，贴上官网[ReactiveX](https://github.com/ReactiveX)
> **官方定义：**在ReactiveX当中，很多指令可能是并行执行的，之后他们的执行结果才会被观察者捕获，顺序是不确定的。未达到这个目的，你定义一种获取和变换数据的机制，而不是调用一个方法。这种机制下，存在一个可观察对象(Observable)，观察者(Observer)订阅(Subscribe)它。
> 
> 这是个神奇的语言，有篇帖子戏称，这是种万能的代码，然后在你仔细研究过这个之后，你会不由自主的同意这个观点。
> 附上个人的理解，ReactiveX是一个将数据库层面的处理方式，提升到代码级别上的一套框架。让Coder可以在代码中更好的对数据进行处理。
> 如果觉得官网文档看不懂，这里贴一个[链接](https://mcxiaoke.gitbooks.io/rxdocs/content/index.html)，是github对这个库文档的翻译，英文不好的请猛戳。

#### Observables
> 在ReactiveX当中，很多指令可能是并行执行的，之后他们的执行结果才会被观察者捕获，顺序是不确定的。未达到这个目的，你定义一种获取和变换数据的机制，而不是调用一个方法。这种机制下，存在一个可观察对象(Observable)，观察者(Observer)订阅(Subscribe)它。

#### 回调函数
下面是一个比较完整的例子
```
// 三个回调函数
def myOnNext = { item -> /* do something useful with item */ };
def myError = { throwable -> /* react sesibly to a failed call */};
def myComplete = { /* clean up after the final response */ };

// 一个观察者对象
def myObservable = someMethod(itesParameters);
// 调用
myObservable.subscribe(myOnNext,myError,myComplete);

```

#### 取消订阅

```
// 获得取消订阅的对象
Subscription subscription = Observable.just()               .subscribe();

// Observable会停止发送新的数据项
subscription.unsubscribe();

```

#### Observable的"冷"与"热"
> 根据Observable的实现会决定，Observable是何时开始发射数据
> 一个"热"Observable可能一创建完就发射数据
> 一个"冷"Observable可能会一直等待，知道有观察者订阅它，才开始发射数据

#### 精髓- - -操作符
> 对于ReactiveX而言，Observable和Observer只是个开始
> ReactiveX真正强大的地方是在于操作符的组合。

下面是常用的操作符列表：
- **创建操作** Create, Defer, Empty/Never/Throw, From, Interval, Just, Range, Repeat, Start, Timer
- **变换操作** Buffer, FlatMap, GroupBy, Map, Scan和Window
- **过滤操作** Debounce, Distinct, ElementAt, Filter, First, IgnoreElements, Last, Sample, Skip, SkipLast, Take, TakeLast
- **组合操作** And/Then/When, CombineLatest, Join, Merge, StartWith, Switch, Zip
- **错误处理** Catch和Retry
- **辅助操作** Delay, Do, Materialize/Dematerialize, ObserveOn, Serialize, Subscribe, SubscribeOn, TimeInterval, Timeout, Timestamp, Using
- **条件和布尔操作** All, Amb, Contains, DefaultIfEmpty, SequenceEqual, SkipUntil, SkipWhile, TakeUntil, TakeWhile
- **算术和集合操作** Average, Concat, Count, Max, Min, Reduce, Sum
- **转换操作** To
- **连接操作** Connect, Publish, RefCount, Replay
- **反压操作**用于增加特殊的流程控制策略的操作符

