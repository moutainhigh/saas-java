package com.hq.chainStore.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.chainStore.service.order.data.SimpleMaterial;

@Deprecated
public class UpdateOrderMaterialForm {
	private List<SimpleMaterial> materials = new ArrayList<SimpleMaterial>();

	public static UpdateOrderMaterialForm newInstance() {
		return new UpdateOrderMaterialForm();
	}

	public List<SimpleMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(List<SimpleMaterial> materials) {
		this.materials = materials;
	}

}
