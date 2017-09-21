// Generated code from Butter Knife. Do not modify!
package com.khilman.cashnoteapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TambahCatatanActivity_ViewBinding implements Unbinder {
  private TambahCatatanActivity target;

  private View view2131427427;

  private View view2131427428;

  @UiThread
  public TambahCatatanActivity_ViewBinding(TambahCatatanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TambahCatatanActivity_ViewBinding(final TambahCatatanActivity target, View source) {
    this.target = target;

    View view;
    target.edtPengeluaran = Utils.findRequiredViewAsType(source, R.id.edtPengeluaran, "field 'edtPengeluaran'", EditText.class);
    target.edtCatatan = Utils.findRequiredViewAsType(source, R.id.edtCatatan, "field 'edtCatatan'", EditText.class);
    view = Utils.findRequiredView(source, R.id.edtTanggal, "field 'edtTanggal' and method 'onViewClicked'");
    target.edtTanggal = Utils.castView(view, R.id.edtTanggal, "field 'edtTanggal'", TextView.class);
    view2131427427 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnSimpan, "field 'btnSimpan' and method 'onViewClicked'");
    target.btnSimpan = Utils.castView(view, R.id.btnSimpan, "field 'btnSimpan'", Button.class);
    view2131427428 = view;
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
    TambahCatatanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtPengeluaran = null;
    target.edtCatatan = null;
    target.edtTanggal = null;
    target.btnSimpan = null;

    view2131427427.setOnClickListener(null);
    view2131427427 = null;
    view2131427428.setOnClickListener(null);
    view2131427428 = null;
  }
}
