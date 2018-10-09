package com.hq.chainClient.service.chainProduct.apiData;

public class ChainProductUpdateForm {
	private int updateType;

	private AddProductInfoData addProductInfoData;
	private UpdateProductInfoData updateProductInfoData;
	private RemoveProductInfoData removeProductInfoData;
	private UpdateProductStateData updateProductStateData;
	private BatchUpdateProductStateData batchUpdateProductStateData;

	private AddProductTypeData addProductTypeData;
	private UpdateProductTypeData updateProductTypeData;
	private RemoveProductTypeData removeProductTypeData;

	private ProductAllotForm productAllotForm;
	private ProductBatchAllotForm productBatchAllotForm;
	
	public static ChainProductUpdateForm newInstance() {
		return new ChainProductUpdateForm();
	}
	
	public ChainProductUpdateType getChainProductUpdateType() {
		return ChainProductUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public void setUpdateTypeEnum(ChainProductUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}

	public ChainProductUpdateType getUpdateTypeEnum() {
		return ChainProductUpdateType.valueOf(this.updateType);
	}

	public AddProductInfoData getAddProductInfoData() {
		return addProductInfoData;
	}

	public void setAddProductInfoData(AddProductInfoData addProductInfoData) {
		this.addProductInfoData = addProductInfoData;
	}

	public UpdateProductInfoData getUpdateProductInfoData() {
		return updateProductInfoData;
	}

	public void setUpdateProductInfoData(
			UpdateProductInfoData updateProductInfoData) {
		this.updateProductInfoData = updateProductInfoData;
	}

	public RemoveProductInfoData getRemoveProductInfoData() {
		return removeProductInfoData;
	}

	public void setRemoveProductInfoData(
			RemoveProductInfoData removeProductInfoData) {
		this.removeProductInfoData = removeProductInfoData;
	}

	public UpdateProductStateData getUpdateProductStateData() {
		return updateProductStateData;
	}

	public void setUpdateProductStateData(
			UpdateProductStateData updateProductStateData) {
		this.updateProductStateData = updateProductStateData;
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

	public void setUpdateProductTypeData(
			UpdateProductTypeData updateProductTypeData) {
		this.updateProductTypeData = updateProductTypeData;
	}

	public RemoveProductTypeData getRemoveProductTypeData() {
		return removeProductTypeData;
	}

	public void setRemoveProductTypeData(
			RemoveProductTypeData removeProductTypeData) {
		this.removeProductTypeData = removeProductTypeData;
	}

	public BatchUpdateProductStateData getBatchUpdateProductStateData() {
		return batchUpdateProductStateData;
	}

	public void setBatchUpdateProductStateData(
			BatchUpdateProductStateData batchUpdateProductStateData) {
		this.batchUpdateProductStateData = batchUpdateProductStateData;
	}

	public ProductAllotForm getProductAllotForm() {
		return productAllotForm;
	}

	public void setProductAllotForm(ProductAllotForm productAllotForm) {
		this.productAllotForm = productAllotForm;
	}

	public ProductBatchAllotForm getProductBatchAllotForm() {
		return productBatchAllotForm;
	}

	public void setProductBatchAllotForm(ProductBatchAllotForm productBatchAllotForm) {
		this.productBatchAllotForm = productBatchAllotForm;
	}

}
