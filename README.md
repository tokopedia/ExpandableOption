# Expandable Option

This library is used to show a layout expandable with kind of header option like switch, radio button, checkbox, etc.

<img src="expandable_sample.gif?raw=true" alt="" width="240" />

## Usage
====

### ExpandableOptionRadio

#### Usage
Use RadioGroupExpandable as a RadioGroup.

#### Example Code - ExpandableOptionRadio

```java
baseExpandableOptionObject.setExpand(boolean isChecked);
baseExpandableOptionObject.toggle();
baseExpandableOptionObject.isExpanded();

baseExpandableOptionObject.setTitleText("expandable radio");

//set listener of expandable view when collapse or expand
baseExpandableOptionObject.setExpandableListener(new BaseExpandableOption.ExpandableListener() {
            @Override
            public void onExpandViewChange(boolean isExpand) {
                //do something when expand/collapse
            }
        });
```

#### Example Code XML - ExpandableOptionRadio and RadioGroupExpandable

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.tokopedia.expandable.RadioGroupExpandable
    android:id="@+id/radio_group"
    android:layout_width="368dp"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <com.tokopedia.expandable.ExpandableOptionRadio
        android:id="@+id/radio1"
        app:eo_title="title switch"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/white">
    
        <include layout="@layout/include_content_option" />

    </com.tokopedia.expandable.ExpandableOptionRadio>

    <com.tokopedia.expandable.ExpandableOptionRadio
        android:id="@+id/radio2"
        app:eo_title="title switch"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/white">

        <include layout="@layout/include_content_option" />
    </com.tokopedia.expandable.ExpandableOptionRadio>
</com.tokopedia.expandable.RadioGroupExpandable>
```

#### Example Code XML - ExpandableOptionChecked

```xml
<com.tokopedia.expandable.ExpandableOptionChecked
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/white"
        app:checked="true"
        app:title_option="title switch">

        <include layout="@layout/include_content_option"/>

</com.tokopedia.expandable.ExpandableOptionChecked>
```

### Example Code XML - ExpandableOptionSwitch

```xml
<com.tokopedia.expandable.ExpandableOptionSwitch
        android:background="@android:color/white"
        app:title_option="title switch"
        app:checked="true"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <include layout="@layout/include_content_option" />

</com.tokopedia.expandable.ExpandableOptionSwitch>
```

### Example Code XML - ExpandableOptionArrow

```xml
<com.tokopedia.expandable.ExpandableOptionArrow
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/darker_gray"
            app:resource_image_arrow_down="@drawable/ic_down"
            app:resource_image_arrow_up="@drawable/ic_up"
            app:checked_option="true"
            app:eo_use_rotate_animation="true"
            app:title_option="Expandable with custom arrow">

            <include layout="@layout/include_content_option" />

</com.tokopedia.expandable.ExpandableOptionArrow>
```

### Example Code XML - Custom ExpandableOption Style

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

#### Example of Custom Style - XML

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
### Example Code XML - Custom ExpandableOption Header
```java
View headerView = LayoutInflater.from(this).inflate(R.layout.custom_header,
                (ViewGroup)baseExpandableOption.getRootView(), false);
baseExpandableOption.setHeaderView(headerView);
```

or custom header in XML
```xml
app:eo_header_layout="@layout/custom_header"
```

### Attributes

|attribute name|description|
|:-:|:-:|
|title_option|title of expandable option|
|eo_checked|if set to true, default is checked|
|resource_image_arrow_up|image resource for arrow UP only for ExpandableOptionArrow|
|resource_image_arrow_down|image resource for arrow DOWN only for ExpandableOptionArrow|
|eo_use_rotate_animation|if set to true, will use rotate animation for arrow for ExpandableOptionArrow|
|eo_header_layout|layout resource for header if you need custom header, can also be set programmatically|
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
