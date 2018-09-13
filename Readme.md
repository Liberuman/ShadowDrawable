[![](https://jitpack.io/v/JuHonggang/ShadowDrawable.svg)](https://jitpack.io/#JuHonggang/ShadowDrawable)
![image](https://img.shields.io/badge/build-passing-brightgreen.svg)
[![image](https://img.shields.io/packagist/l/doctrine/orm.svg)](https://github.com/JuHonggang/ShadowDrawable/blob/master/LICENSE)


#### 添加依赖

在项目的build.gradle中添加：

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io'
        }
      }
    }
在APP模块下的build.gradle中添加依赖：

    dependencies {
        implementation 'com.github.JuHonggang:ShadowDrawable:0.1'
    }

#### 使用

通过一行代码即可实现阴影效果

    ShadowDrawable.setShadowDrawable(textView1, Color.parseColor("#3D5AFE"), dpToPx(8),
        Color.parseColor("#66000000"), dpToPx(10), 0, 0);

详细介绍可参考：[Android开发中阴影效果的实现](
http://tinycoder.cc/2018/04/26/Android%E5%BC%80%E5%8F%91%E4%B8%AD%E9%98%B4%E5%BD%B1%E6%95%88%E6%9E%9C%E7%9A%84%E5%AE%9E%E7%8E%B0/)

#### 阴影效果

![image](http://od186sz8s.bkt.clouddn.com/shadow.jpg)

#### License

Copyright (c) 2018 Freeman

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.