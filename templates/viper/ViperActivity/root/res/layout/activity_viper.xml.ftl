<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools">

    <android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.design.widget.AppBarLayout
            android:id="@+id/app_bar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/AppTheme.AppBarOverlay"
            app:elevation="0dp">

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:theme="@style/ToolbarTheme"
                app:contentInsetStartWithNavigation="0dp"
                app:layout_collapseMode="pin"
                style="@style/AppTheme.PopupOverlay"/>

        </android.support.design.widget.AppBarLayout>
        <FrameLayout
            android:id="@+id/content"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1">
        <android.support.v7.widget.RecyclerView
                android:id="@+id/recycler_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:visibility="gone"
        />
        <TextView
                android:id="@+id/error_text"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_gravity="center"
                android:visibility="gone"
        />
        <ProgressBar
                android:id="@+id/progress_bar"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
        />
    </FrameLayout>
    </android.support.design.widget.CoordinatorLayout>
</layout>
