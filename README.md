# LemonKit4Android
你是iOS开发者？还是Android开发者？还是想两个平台都玩玩？<(￣ˇ￣)/

-------

> **————首先你现在变身成为了iOS工程师————**

​	你好，iOS工程师，很高兴能遇到你，你有没有想过转行到Android？但是iOS的UIKit框架深深的把你迷住，突然间转到Android行列什么都不适应，设置控件的圆角还要用XML，开一个新界面还要什么Intent，setClass，ActivityXX.class，在Manifast.xml里面不配置新加的Activity还TM会闪退！这都是什么鬼，完全和iOS不一样啊！！！**这让朕怎么学？**

​	好，那么可爱的iOS工程师，如果现在让你这样写Android代码，你可不可以考虑转行Android呢：

```java
public class MainViewController extends UIViewController {

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        UILabel label = new UILabel(CGRect.make(50, 50, 200, 100));
        label.setText("Hello! LemonKit World.");
        label.setBackgroundColor(UIColor.greenColor());
        this.view.addSubView(label);
    }

}
```

> **————现在你现在变身成为了Android工程师————**

