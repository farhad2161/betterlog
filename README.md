# Android Better Log
Easier and better way to create log message in android.

### Why it's better?
* Tag is optional.
* It automatically add the position of log to your log message so you can find out where it happend.
* Same syntax of android.util.Log and compatible with it.
* You can add some lable in front of your log message.
* You can fully disable logging with just setting a property.

### Usage

First add this to your gradle

```
compile 'com.kia:betterlog:1.0.0'
```

then import it to each class you want

```
import com.kia.betterlog.Log;

public class MyClass {

}
```

You can set optional TAG

```Log.setTag("MYTAG");```

### Log a simple message
```
Log.w("I am warning message.");
Log.d("I am debug message.");
Log.e("I am error message.");
Log.i("I am info message.");
```

sample output

```
W/MYTAG: [com.kia.betterlogapp.MainActivity.writeDummyLog]I am warning message.
```

**The first bracket show where the log message happend.**

### Log with one label
```
Log.d("I am a label", "I am debug message");
```

sample output

```
d/MYTAG: [com.kia.betterlogapp.MainActivity.writeDummyLog][I am a label]I am debug message
```

### Log with multiple label
```
Log.e(Arrays.asList("label1", "label2"), "I am debug message");
```

sample output

```
e/MYTAG: [com.kia.betterlogapp.MainActivity.writeDummyLog][label1][label2]I am error message.
```

### Disabling log
```
Log.setEnable(false);
```

