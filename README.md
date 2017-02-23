# HeadersRecyclerView

Simple library with implemented headers in RecyclerView.

## Getting Started

### Dependency

Include the dependency [Download (.aar)](https://github.com/Kaellah/HeadersRecyclerView/raw/master/simple/libs/headerslib/build/outputs/aar/headerslib-release.aar) and place it in your libs directory:

```groovy
allprojects {
    repositories {
        jcenter()
        flatDir {
            dirs 'libs'
        }
    }
}

// ...

dependencies {
    compile (name:'headerslib', ext:'aar')
    compile 'com.android.support:appcompat-v7:25.2.0'
    compile 'com.android.support:recyclerview-v7:25.2.0' // you need include this too
}
```

### Usage

You need create custom recycler adapter and extands it from HeadersRecyclerViewAdapter.class.

```java
public class SimpleAdapter extends HeadersRecyclerViewAdapter<HeaderData, ItemData, ItemView, HeaderView> {

    public SimpleAdapter(@Nullable List<SectionHeader> headerItemList) {
        super(headerItemList);
    }

    @Override
    protected ItemView obtainItem(View parent) {
        return new ItemView(parent.getContext());
    }

    @Override
    protected HeaderView obtainHeader(View parent) {
        return new HeaderView(parent.getContext());
    }
}
```