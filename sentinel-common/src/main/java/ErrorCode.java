/**
 * 错误信息枚举类
 *
 * @author zzb
 * @version 1.1.0 2017/06/20
 */
public enum ErrorCode {
	/**公共错误码**/
	MY_ERROR(9000, ""),//自定义错误
	CONFIG_ERROR(9001, "配置缺失"),
	DB_ERROR(9002, "数据库访问报错"),
	DATA_NOT_EXIST_ERROR(9003, "数据不存在"),
	DATA_CONVERTER_ERROR(9004, "数据转换失败"),
	ACTION_OPERATE_INFO_ERROR(9005, "操作信息缺失"),
	DUPLICATE(9006, "重复提交"),
	STORE_SERVER_ERROR(9007, "库存server异常"),
	DATA_INCONSIS(9008, "数据不一致"),
	OPERATION_FORBIDDEN(9009,"禁止操作"),

	PARAM_ERROR(100001, "参数非法"),
	PARAM_OUT_RANGE_ERROR(100002, "参数的值超出范围"),


	/**具体业务错误码**/

	PRE_SHELF_PRODUCT_VERIFY_STATUS_ERROR(100002, "商品审核状态不正确"),
	PRE_SHELF_PRODUCT_NOT_EDITEABLE(100005, "该草稿信息已成功提交或删除，请刷新页面再次查看"),
	PRE_SHELF_PRODUCT_NOT_EXIST(100006, "预上架商品不存在"),
	PRE_SHELF_PRODUCT_CREATED(100006, "该商品已创建，无法重复创建"),

	VR_TAG_NOT_EXIST(100010,"标签不存在"),

	PRODUCTION_ON_SHELF(110001,"商品已上架"),
	EXPRESS_NOT_SET(110002,"请先到【物流管理->物流工具】下设置配送方式并设置配送区域和月结卡号"),
	EXPRESS_COMPANY_NOT_SET(110003,"快递公司信息缺失"),
	PRODUCTION_DELETED(110004,"商品已删除"),
	PRODUCTION_NOT_AUDIT_PASSED(110005, "商品未审核通过"),

	PRODUCTION_NONE_EXPRESSTYPE(110011,"请先给商品设置配送方式"),

	NO_VALID_KICKBACK(110006, "没有有效扣点"),
	SUPERSTANDARD_OFF_SHELF(110007, "商品因超标下架，不能进行任何操作。"),

    PRODUCT_CHANGE_APPLY_ID_NOT_EXIST(120001, "商品变更申请不存在"),
    PRODUCT_CHANGE_DELETE_FAIL(120002, "删除商品变更失败"),
	PRODUCT_CHANGE_SUBMIT_FAIL(120003, "提交商品变更失败"),
	PRODUCT_CHANGE_AUDIT_FAIL(120004, "审核商品变更失败"),

	DB_INSERT_ERROR(200003,"数据入库失败"),
	DB_UPDATE_ERROR(200004, "数据更新失败"),
	DB_DELETE_ERROR(200005, "数据删除失败"),

	ERROR_QUERY_CATEGORY_NULL(200006,"查询类目为空"),
	CATEGORY_DISABLE(200007,"类目已禁用"),
	CATEGORY_NOT_THIRD(200008,"类目非三级类目"),

	PRODUCTGROUP_TIME_OVERLAPPING(310000,"商品活动时间有重叠"),
	PRODUCTGROUP_ENDTIME_LESS_STARTTIME(310001, "活动开始时间必须小于结束时间"),
	PRODUCTGROUP_STATUS_ERROR(310002,"商品活动状态有误"),
	PRODUCTGROUP_ID_NULL(310003,"商品活动ID为空"),
	PRODUCTGROUP_REJECT_REASON_NULL(310004,"审核不通过原因为空"),
	PRODUCTGROUP_ENDTIME_LESS_NOW(310005, "活动结束时间已过，请修改"),


	SYSTEM_ERROR(999999, "接口异常");
	private Integer code;
	private String message;

	private ErrorCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
