***Stylise String Chooser***

# Repository Title Goes Here
Add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency
```
dependencies {
	        implementation 'com.github.volobot-admin:String-Picker-Android:v1.3'
	}
```

<img title="Example main color in a dialog" src="https://raw.githubusercontent.com/volobot-admin/String-Picker-Android/master/screenshot-1561442914285.jpg?raw=true" width="400" />

#How to implement
```
<com.volobot.stringchooser.StringChooser
        android:id="@+id/stringChooser"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:selectedColor="@android:color/holo_blue_dark"
        app:notSelectedColor="@android:color/holo_green_light"
        app:selectedSize="32dp"
        app:notSelectedSize="20dp"
        app:notSelectedOpacity="0.3"
        >
```
```
StringChooser stringChooser = findViewById(R.id.stringChooser);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            strings.add("option "+i);
        }
        stringChooser.setStrings(strings);
        stringChooser.setStringChooserCallback(new StringChooser.StringChooserCallback() {
            @Override
            public void onStringPickerValueChange(String s, int position) {
                Log.d(TAG, s);
            }
        });
```
