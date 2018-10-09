package com.hq.customerMS.service.cuser.data;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hq.customerMS.service.cuser.apiData.CUserAddressAddForm;
import com.hq.customerMS.service.cuser.apiData.CUserAddressRemoveForm;
import com.hq.customerMS.service.cuser.apiData.CUserAddressUpdateForm;
import com.hq.customerMS.service.cuser.apiData.CUserChangeDefaultAddressForm;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserBeanHelper {

	public static CUserBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(CUserBeanHelper.class);
	}
	
	public boolean addAddress(CUser cuser, CUserAddressAddForm addForm) {
		if (cuser == null) {
			return false;
		}
		boolean addSuccess = false;
		boolean changeDefaultSuccess = true;
		ReceiverAddress data = addForm.toReceiverAddress();
		long index = addForm.getIndex();
		Map<String, ReceiverAddress> addressMap = cuser.getAddressMap();
		if (!addressMap.containsKey(data.getId()) && cuser.getAddressIdIndex() + 1 == index) {
			addressMap.put(data.getId(), data);
			cuser.setAddressIdIndex(index);
			addSuccess = true;
		}
		if(addForm.getDefaultFlag() == DefaultFlagEnum.IS_DEFAULT.ordinal()) {
			changeDefaultSuccess = changeDefaultAddress(cuser, String.valueOf(index));
		}
		return addSuccess && changeDefaultSuccess;
	}

	public boolean removeAddress(CUser cuser, CUserAddressRemoveForm removeForm) {
		if (cuser == null) {
			return false;
		}
		boolean success = false;
		Map<String, ReceiverAddress> addressMap = cuser.getAddressMap();
		String addressId = String.valueOf(removeForm.getAddressId());
		if (addressMap.containsKey(addressId)) {
			addressMap.remove(addressId);
			success = true;
		}
		return success;
	}
	
	public boolean updateAddress(CUser cuser, CUserAddressUpdateForm updateForm) {
		if (cuser == null) {
			return false;
		}
		boolean updateSuccess = false;
		boolean changeDefaultSuccess = true;
		Map<String, ReceiverAddress> addressMap = cuser.getAddressMap();
		if (addressMap.containsKey(updateForm.getId())) {
			addressMap.put(updateForm.getId(), updateForm.toReceiverAddress());
			updateSuccess = true;
		}
		if(updateForm.getDefaultFlag() == DefaultFlagEnum.IS_DEFAULT.ordinal()) {
			changeDefaultSuccess = changeDefaultAddress(cuser, updateForm.getId());
		}
		return updateSuccess && changeDefaultSuccess;
	}
	
	private boolean changeDefaultAddress(CUser cuser, String addressId) {
		if (cuser == null) {
			return false;
		}
		boolean success = false;
		Map<String, ReceiverAddress> addressMap = cuser.getAddressMap();
		if (addressMap.containsKey(addressId)) {
			Set<Entry<String, ReceiverAddress>> entrySet = addressMap.entrySet();
			for (Entry<String, ReceiverAddress> entry : entrySet) {
				ReceiverAddress address = entry.getValue();
				address.setDefaultFlag(DefaultFlagEnum.NON_DEFAULT.ordinal());
				if (entry.getKey().equals(addressId)) {
					address.setDefaultFlag(DefaultFlagEnum.IS_DEFAULT.ordinal());
				}
			}
			success = true;
		}
		return success;
	}
	
	/**
	 * 如果在地址列表页面设置默认地址，使用此方法
	 * @param cuser
	 * @param changeDefaultAddressForm
	 * @return
	 */
	public boolean changeDefaultAddress(CUser cuser, CUserChangeDefaultAddressForm changeDefaultAddressForm) {
		String addressId = String.valueOf(changeDefaultAddressForm.getAddressId());
		return changeDefaultAddress(cuser, addressId);
	}

}
