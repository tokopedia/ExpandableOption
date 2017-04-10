# Expandable Option

This library is used to show a layout expandable with kind of header option like switch, radio button, checkbox, etc.

<img src="record_expandable_option.gif?raw=true" alt="" width="240" />

## Usage
====

### ExpandableOptionRadio

#### Usage
this option radio is used to expand view when status checked radio button is change.
you need to use RadioGroupExpandable if you want to use it on RadioGroup.
because radio group of android couldn't handle radio button inside another child view.

#### Code ExpandableOptionRadio

```java
ExpandableOptionRadio expandableRadio = (ExpandableOptionRadio) findViewById(R.id.expandableOptionRadio)

// toggle expand, collapse
expandableRadio.toggle();
// expand
expandableRadio.expand();
// collapse
expandableRadio.collapse();

//set checked radio
expandableRadio.setChecked(true);

//get status checked of radio
boolean isChecked = expandableRadio.isChecked();

//set title expandable
expandableRadio.setTitleText("expandable radio");

//set listener of expandable view when collapse or expand
expandableRadio.setExpandableListener(new BaseExpandableOption.ExpandableListener() {
            @Override
            public void onExpandViewChange(boolean isExpand) {
                //do something
            }
        });
```

#### Code RadioGroupExpandable

```java
RadioGroupExpandable radioGroup = (RadioGroupExpandable) findViewById(R.id.expandableRadioGroup);

//set listener radio group when status radio button checked change
radioGroup.setOnCheckedChangeListener(new TopAdsCustomRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TopAdsCustomRadioGroup group, @IdRes int checkedId) {
                //do something
            }
        });

//get id of radio button which checked
radioGroup.getCheckedRadioButtonId();

```

#### Layout xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.tokopedia.expandable.sample.RadioButtonExpandableActivity">

    <com.tokopedia.expandable.RadioGroupExpandable
        android:layout_width="368dp"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <com.tokopedia.expandable.ExpandableOptionRadio
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/white"
            app:radio_id=@+id/radio_id_1
            app:title_option="title switch">

            <include layout="@layout/include_content_option" />

        </com.tokopedia.expandable.ExpandableOptionRadio>

        <com.tokopedia.expandable.ExpandableOptionRadio
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/white"
            app:radio_id=@+id/radio_id_2
            app:title_option="title switch">

            <include layout="@layout/include_content_option" />
        </com.tokopedia.expandable.ExpandableOptionRadio>

        <com.tokopedia.expandable.ExpandableOptionRadio
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/white"
            app:radio_id=@+id/radio_id_3
            app:checked_option="true"
            app:title_option="title switch">

            <include layout="@layout/include_content_option" />
        </com.tokopedia.expandable.ExpandableOptionRadio>
    </com.tokopedia.expandable.RadioGroupExpandable>

</android.support.constraint.ConstraintLayout>
```

### ExpandableOptionChecked

#### Usage
this option checked is used to expand view when status checked check box is change.

#### Code ExpandableOptionChecked

```java
ExpandableOptionChecked expandableChecked = (ExpandableOptionChecked) findViewById(R.id.expandableOptionChecked)

// toggle expand, collapse
expandableChecked.toggle();
// expand
expandableChecked.expand();
// collapse
expandableChecked.collapse();

//set checked option checkbox
expandableChecked.setChecked(true);

//get status checked of checkbox
boolean isChecked = expandableChecked.isChecked();

//set title expandable
expandableChecked.setTitleText("expandable checked");

//set listener of expandable view when collapse or expand
expandableChecked.setExpandableListener(new BaseExpandableOption.ExpandableListener() {
            @Override
            public void onExpandViewChange(boolean isExpand) {
                //do something
            }
        });
```

#### Layout xml

```xml
<com.tokopedia.expandable.ExpandableOptionChecked
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/white"
        app:checked_option="true"
        app:title_option="title switch">

        <include layout="@layout/include_content_option"/>

</com.tokopedia.expandable.ExpandableOptionChecked>
```

### ExpandableOptionSwitch

#### Usage
this option checked is same with option checked, but this option use switch compat button to indicating expand view.


#### Layout xml

```xml
<com.tokopedia.expandable.ExpandableOptionSwitch
        android:background="@android:color/white"
        app:title_option="title switch"
        app:checked_option="true"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <include layout="@layout/include_content_option" />

</com.tokopedia.expandable.ExpandableOptionSwitch>
```

### ExpandableOptionArrow

#### Usage
this option checked is same with option checked, but this option use image arrow button to indicating expand view.
you can change resource of image arrow by xml.

#### Layout xml

```xml
<com.tokopedia.expandable.ExpandableOptionArrow
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/darker_gray"
            app:resource_image_arrow_down="@drawable/ic_down"
            app:resource_image_arrow_up="@drawable/ic_up"
            app:checked_option="true"
            app:title_option="Expandable with custom arrow">

            <include layout="@layout/include_content_option" />

</com.tokopedia.expandable.ExpandableOptionArrow>
```


### Custom style expandable option

#### Usage
you can customize item inside of expandable option. for example you can change header of expandable option.

#### Layout xml

```xml
<com.tokopedia.expandable.ExpandableOptionChecked
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="15dp"
            android:background="@android:color/darker_gray"
            app:expandable_option_theme="@style/CustomExpandableStyle"
            app:title_option="Expandable view with custom style">

            <include layout="@layout/include_content_option" />
</com.tokopedia.expandable.ExpandableOptionChecked>
```

#### style xml

``` style xml
<style name="CustomExpandableStyle" parent="style_expandable_option_default">
        <item name="eo_header_style">@style/eo_header_style_custom</item>
        <item name="eo_text_option_style">@style/eo_text_option_style_custom</item>
</style>

<style name="eo_header_style_custom" parent="eo_header_style">
        <item name="android:background">@color/colorPrimary</item>
</style>

<style name="eo_text_option_style_custom" parent="eo_text_option_style">
        <item name="android:textStyle">bold</item>
        <item name="android:textColor">@color/colorAccent</item>
</style>
```


### Attributes

|attribute name|description|
|:-:|:-:|
|title_option|title of expandable option|
|checked_option|status of checked option|
|radio_id|id of radio button which is used to get radio id with radio group|
|resource_image_arrow_up|option to change resource image up of expandable option arrow)|
|resource_image_arrow_down|option to change resource image up of expandable option arrow|
|expandable_option_theme|theme to use for even more custom styling of expandable option layout. it is recommended that it should extend|

### ExpandableOption style attributes
A list of `expandable_option_theme` attributes responsible for styling of ExpandableOption's child views.

|Attribute name|Description|
|:-:|:-:|
| *eo_radio_button_style*|Used to styling radio button of radio option expandable|
| *eo_expandable_style*|Used to styling layout content of expandable|
| *eo_switch_style*|Used to styling layout switch compat button of switch option expandable|
| *eo_text_option_style*|Used to styling layout textview of title option expandable|
| *eo_checked_style*|Used to styling layout checkbox of checked option expandable|
| *eo_arrow_style*|Used to styling layout image arrow of arrow option expandable|
| *eo_header_style*|Used to styling layout header of expandable option|

License
-------

    Copyright 2017 Tokopedia

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
