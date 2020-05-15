import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 操作系统类
 *
 * @author zzb
 * @version 1.1.0 2017/06/20
 */
public class SysActionDto implements Serializable {


	private static final long serialVersionUID = -165143745350517183L;
	/**
	 * 操作所属模块名称
	 */
//	@NotNull(message = "操作所属模块名称不能为空" ,groups = SysActionDtoGroup.class)
	private String actionModule;
	/**
	 * 所属系统名称
	 */
	@NotNull(message = "所属系统名称不能为空",groups = SysActionDtoGroup.class)
	private String sysName;
	/**
	 * 操作人
	 */
	@NotNull(message = "操作人不能为空",groups = SysActionDtoGroup.class)
	private String actionBy;

	public String getActionModule() {
		return actionModule;
	}

	public void setActionModule(String actionModule) {
		this.actionModule = actionModule;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}



	/**自定义公共方法
	 * @param data  详见{@link SysActionDto}
	 * @return String
	 */
	public static String validator(SysActionDto data) {
		String result="";
		if(data==null)
			return "操作信息不能为空";
		if(data.getActionBy()==null){
			result+="操作人不能为空;";
		}
//		if(data.getActionModule()==null){
//			result+="所属系统名称不能为空;";
//		}
		if(data.getSysName()==null){
			result+="所属系统名称不能为空;";
		}
		return result;
	}
}
