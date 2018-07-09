package com.hu.zhcc.common.utils;


import com.hu.zhcc.common.constant.MsgConstant;
import com.hu.zhcc.common.constant.SystemConstant;
import com.hu.zhcc.common.entity.Result;

/**
 * 通用工具类
 *
 * Created by hulichao on 2018/6/20
 */
public class CommonUtils {

	/**
	 * 对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断整数是否大于零
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isIntThanZero(int number) {
		if (number > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 新增，修改提示
	 * @param count
	 * @return
	 */
	public static Result msg(int count) {
		if(isIntThanZero(count)){
			return Result.success(MsgConstant.MSG_OPERATION_SUCCESS);
		}
		return Result.fail(MsgConstant.MSG_OPERATION_FAILED);
	}
	
	/**
	 * 查询详情提示
	 * @param data
	 * @return
	 */
	public static Result msg(Object data) {
		if(isNullOrEmpty(data)){
			return Result.fail(MsgConstant.MSG_INIT_FORM);
		}
		return Result.success().put(SystemConstant.DATA_ROWS, data);
	}
	
	/**
	 * 返回数据
	 * @param data
	 * @return
	 */
	public static Result msgNotCheckNull(Object data) {
		return Result.success().put(SystemConstant.DATA_ROWS, data);
	}
	
	/**
	 * 删除提示
	 * @param total
	 * @param count
	 * @return
	 */
	public static Result msg(Object[] total, int count) {
		if(total.length == count){
			return Result.success(MsgConstant.MSG_OPERATION_SUCCESS);
		}else{
			if(isIntThanZero(count)){
				return Result.fail(MsgConstant.removeFailed(total.length, count));
			}else{
				return Result.fail(MsgConstant.MSG_OPERATION_FAILED);
			}
		}
	}
	
}
