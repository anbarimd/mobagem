//package net.mha.framework.myweb.gen;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//import javax.annotation.PostConstruct;
//
//import net.mha.framework.myweb.userinfo.server.api.IUserInfoAPI;
//import net.mha.framework.myweb.userinfo.server.entity.UserInfoEntity;
//import net.mha.framework.myweb.userinfo.shared.dto.constant.UserRegisterStatus;
//import net.mha.framework.sec.authenticate.server.user.api.IBaseUserAPI;
//import net.mha.framework.sec.authenticate.server.user.entity.BaseUserEntity;
//import net.mha.framework.sec.utils.HashingUtil;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//import org.springframework.transaction.support.TransactionTemplate;
//
//@Component
//public class DataGenerator extends
//		ClassPathScanningCandidateComponentProvider {
//
//	@Autowired
//	IBaseUserAPI baseUserAPI;
//
//	@Autowired
//	IUserInfoAPI userInfoAPI;
//
//	@Autowired
//	private PlatformTransactionManager platformTransactionManager;
//
//	public DataGenerator() {
//		super(false);
//	}
//	
//	private static final Integer userInfoCounter = 23;
//
//	@PostConstruct
//	public void initialize() {
//
//		TransactionTemplate transactionTemplate = new TransactionTemplate(
//				platformTransactionManager);
//		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus status) {
//
//				for (int i = 0; i < userInfoCounter; i++) {
//					UserInfoEntity userInfo = new UserInfoEntity();
//					userInfo.setFirstName(getRandomFirstName());
//					userInfo.setLastName(getRandomLastName());
//					userInfo.setRegisterDate(getRandomDate());
//					userInfo.setEmail(getRandomString());
//					userInfo.setPhoneNumber(getRandomString());
//					userInfo.setUserRegisterStatus(UserRegisterStatus.in_progress);
//
//					BaseUserEntity baseUser = new BaseUserEntity();
//					String userName = userNames.get(i);
//					baseUser.setUsername(userName);
//					baseUser.setPassword(HashingUtil.sha1(userName));
//					try {
//						Long baseUserId = baseUserAPI.save(baseUser);
//						BaseUserEntity baseUserSaved = baseUserAPI
//								.findById(baseUserId);
//
//						userInfo.setBaseUser(baseUserSaved);
//						userInfoAPI.save(userInfo);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//
//			}
//		});
//
//	}
//
//	private String getRandomFirstName() {
//		Random r = new Random();
//		return firstNames.get(r.nextInt(firstNames.size()));
//	}
//
//	private String getRandomLastName() {
//		Random r = new Random();
//		return lastNames.get(r.nextInt(lastNames.size()));
//	}
//
//	private String getRandomNumber() {
//		Random r = new Random();
//		int nextInt = r.nextInt(9);
//		return String.valueOf(nextInt++);
//	}
//
//	private String getRandomNumber(int num) {
//		Random r = new Random();
//		int nextInt = r.nextInt(num);
//		return String.valueOf(nextInt++);
//	}
//
//	private String getRandomDate() {
//		Random r = new Random();
//		return dates.get(r.nextInt(dates.size()));
//	}
//
//	private String getRandomString() {
//
//		Random r = new Random();
//		int nextInt = r.nextInt(10);
//
//		String str = "";
//
//		for (int i = 0; i < nextInt; i++) {
//			int next = r.nextInt(32);
//			str += alefba.charAt(next);
//		}
//
//		return str;
//
//	}
//
//	private String getRandomNationalCode() {
//
//		String nationalCode = "";
//
//		for (int i = 0; i < 10; i++) {
//			nationalCode += getRandomNumber();
//		}
//
//		return nationalCode;
//
//	}
//
//	List<String> userNames = Arrays.asList("ali", "hasan", "hosein", "sajad",
//			"bagher", "sadegh", "mousa", "reza", "javad", "hadi", "asgar",
//			"mahdi", "fateme", "zahra", "zeynab", "sedighe", "masoume",
//			"tahere", "esmaeel", "monza", "morteza", "alibagher", "mammadali");
//
//	List<String> firstNames = Arrays.asList("علی", "حسن", "حسین", "سجاد",
//			"باقر", "صادق", "موسی", "رضا", "جواد", "هادی", "عسکر", "مهدی",
//			"فاطمه", "زهرا", "زینب", "صدیقه", "طاهره", "معصومه");
//
//	List<String> lastNames = Arrays.asList("علوی", "حسنی", "حسینی", "سجادی",
//			"باقری", "صادقی", "موسی زاده", "رضایی", "جوادی", "هادی زاده",
//			"عسکری", "مهدوی", "فاطمی", "زهرایی", "زینبی", "صدیقی", "طاهری",
//			"معصومی");
//
//	List<String> dates = Arrays.asList("1393/3/3", "1392/2/2", "1391/1/1",
//			"1390/10/10", "13989/8/9", "1388/8/8", "1387/6/7", "1369/5/14",
//			"1380/2/20", "1365/5/26", "1364/7/13", "1366/3/14", "1359/11/11",
//			"1348/1/1", "1364/12/4", "1335/8/6", "1339/9/9", "1385/5/2");
//
//	String alefba = "ابپتثجچحخدذرزژسشصضطظعغفقکگلمنوهی";
//
//}
