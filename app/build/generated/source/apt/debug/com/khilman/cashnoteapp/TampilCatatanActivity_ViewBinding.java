// Generated code from Butter Knife. Do not modify!
package com.khilman.cashnoteapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TampilCatatanActivity_ViewBinding implements Unbinder {
  private TampilCatatanActivity target;

  @UiThread
  public TampilCatatanActivity_ViewBinding(TampilCatatanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TampilCatatanActivity_ViewBinding(TampilCatatanActivity target, View source) {
    this.target = target;

    target.listCatatan = Utils.findRequiredViewAsType(source, R.id.listCatatan, "field 'listCatatan'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TampilCatatanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listCatatan = null;
  }
}
