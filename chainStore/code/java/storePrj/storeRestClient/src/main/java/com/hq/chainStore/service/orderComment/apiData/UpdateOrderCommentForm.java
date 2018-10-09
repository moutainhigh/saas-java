package com.hq.chainStore.service.orderComment.apiData;


public class UpdateOrderCommentForm {
	private long storeId;
	private int updateType;

	private SaveBeauticianCommentForm saveBeauticianCommentForm;

	public static UpdateOrderCommentForm newInstance() {
		return new UpdateOrderCommentForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public SaveBeauticianCommentForm getSaveBeauticianCommentForm() {
		return saveBeauticianCommentForm;
	}

	public void setSaveBeauticianCommentForm(
			SaveBeauticianCommentForm saveBeauticianCommentForm) {
		this.saveBeauticianCommentForm = saveBeauticianCommentForm;
	}

}
