package org.opendatakit.aggregate.client.widgets;

import org.opendatakit.aggregate.client.AggregateUI;
import org.opendatakit.aggregate.client.SecureGWT;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class DownloadableCheckBox extends ACheckBoxBase implements ValueChangeHandler<Boolean> {
  private String formId;
  
  public DownloadableCheckBox(String formId, Boolean downloadable) {
    super();
    this.formId = formId;
    setValue(downloadable);
    addValueChangeHandler(this);
  }

  @Override
  public void onValueChange(ValueChangeEvent<Boolean> event) {
    super.onValueChange(event);
    
    SecureGWT.getFormAdminService().setFormDownloadable(formId, event.getValue(), new AsyncCallback<Boolean> () {
      @Override
      public void onFailure(Throwable caught) {
        AggregateUI.getUI().reportError(caught);
      }

      @Override
      public void onSuccess(Boolean result) {
        AggregateUI.getUI().clearError();
      }
    });
  }

}