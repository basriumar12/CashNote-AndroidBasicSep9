// Generated code from Butter Knife. Do not modify!
package com.khilman.cashnoteapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131427424;

  private View view2131427423;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.imgTambah, "field 'imgTambah' and method 'onViewClicked'");
    target.imgTambah = Utils.castView(view, R.id.imgTambah, "field 'imgTambah'", ImageView.class);
    view2131427424 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imgLihatCatatan, "field 'imgLihatCatatan' and method 'onViewClicked'");
    target.imgLihatCatatan = Utils.castView(view, R.id.imgLihatCatatan, "field 'imgLihatCatatan'", ImageView.class);
    view2131427423 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgTambah = null;
    target.imgLihatCatatan = null;

    view2131427424.setOnClickListener(null);
    view2131427424 = null;
    view2131427423.setOnClickListener(null);
    view2131427423 = null;
  }
}
