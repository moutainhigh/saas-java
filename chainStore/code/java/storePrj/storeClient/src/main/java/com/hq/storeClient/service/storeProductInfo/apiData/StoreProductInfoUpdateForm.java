package com.hq.storeClient.service.storeProductInfo.apiData;

public class StoreProductInfoUpdateForm {
	private int updateType;
	private long storeId;

	private AddProductInfoData addProductInfoData;
	private UpdateProductInfoData updateProductInfoData;
	private RemoveProductInfoData removeProductInfoData;
	private UpdateProductStateData updateProductStateData;
	private BatchUpdateProductStateData batchUpdateProductStateData;// 批量上下架

	private AddProductTypeData addProductTypeData;
	private UpdateProductTypeData updateProductTypeData;
	private RemoveProductTypeData removeProductTypeData;

	private AddProductToTopData addProductToTopData;
	private CancelProductFromTopData cancelProductFromTopData;
	
	/*************************连锁店数据同步**************************************/
	private ProductBatchCancelForm productBatchCancelForm;
	private ProductBatchPullForm productBatchPullForm;
	private ProductCancelForm productCancelForm;
	private ProductPullForm productPullForm;

	public static StoreProductInfoUpdateForm newInstance() {
		return new StoreProductInfoUpdateForm();
	}

	public void setUpdateTypeEnum(StoreProductInfoUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public UpdateProductStateData getUpdateProductStateData() {
		return updateProductStateData;
	}

	public void setUpdateProductStateData(UpdateProductStateData updateProductStateData) {
		this.updateProductStateData = updateProductStateData;
	}

	public BatchUpdateProductStateData getBatchUpdateProductStateData() {
		return batchUpdateProductStateData;
	}

	public void setBatchUpdateProductStateData(BatchUpdateProductStateData batchUpdateProductStateData) {
		this.batchUpdateProductStateData = batchUpdateProductStateData;
	}

	public UpdateProductInfoData getUpdateProductInfoData() {
		return updateProductInfoData;
	}

	public void setUpdateProductInfoData(UpdateProductInfoData updateProductInfoData) {
		this.updateProductInfoData = updateProductInfoData;
	}

	public RemoveProductInfoData getRemoveProductInfoData() {
		return removeProductInfoData;
	}

	public void setRemoveProductInfoData(RemoveProductInfoData removeProductInfoData) {
		this.removeProductInfoData = removeProductInfoData;
	}

	public AddProductTypeData getAddProductTypeData() {
		return addProductTypeData;
	}

	public void setAddProductTypeData(AddProductTypeData addProductTypeData) {
		this.addProductTypeData = addProductTypeData;
	}

	public UpdateProductTypeData getUpdateProductTypeData() {
		return updateProductTypeData;
	}

	public void setUpdateProductTypeData(UpdateProductTypeData updateProductTypeData) {
		this.updateProductTypeData = updateProductTypeData;
	}

	public RemoveProductTypeData getRemoveProductTypeData() {
		return removeProductTypeData;
	}

	public void setRemoveProductTypeData(RemoveProductTypeData removeProductTypeData) {
		this.removeProductTypeData = removeProductTypeData;
	}

	public AddProductInfoData getAddProductInfoData() {
		return addProductInfoData;
	}

	public void setAddProductInfoData(AddProductInfoData addProductInfoData) {
		this.addProductInfoData = addProductInfoData;
	}

	public AddProductToTopData getAddProductToTopData() {
		return addProductToTopData;
	}

	public void setAddProductToTopData(AddProductToTopData addProductToTopData) {
		this.addProductToTopData = addProductToTopData;
	}

	public CancelProductFromTopData getCancelProductFromTopData() {
		return cancelProductFromTopData;
	}

	public void setCancelProductFromTopData(CancelProductFromTopData cancelProductFromTopData) {
		this.cancelProductFromTopData = cancelProductFromTopData;
	}

	public ProductBatchCancelForm getProductBatchCancelForm() {
		return productBatchCancelForm;
	}

	public void setProductBatchCancelForm(ProductBatchCancelForm productBatchCancelForm) {
		this.productBatchCancelForm = productBatchCancelForm;
	}

	public ProductBatchPullForm getProductBatchPullForm() {
		return productBatchPullForm;
	}

	public void setProductBatchPullForm(ProductBatchPullForm productBatchPullForm) {
		this.productBatchPullForm = productBatchPullForm;
	}

	public ProductCancelForm getProductCancelForm() {
		return productCancelForm;
	}

	public void setProductCancelForm(ProductCancelForm productCancelForm) {
		this.productCancelForm = productCancelForm;
	}

	public ProductPullForm getProductPullForm() {
		return productPullForm;
	}

	public void setProductPullForm(ProductPullForm productPullForm) {
		this.productPullForm = productPullForm;
	}

}
