package dbprojecttest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//Area 저장할 클래스 
class Area {
	String id;
	String address;
	String start_date;
	String end_date;
	String weekday_start_time;
	String weekday_end_time;
	String weekend_start_time;
	String weekend_end_time;
	String money;
	String is_alchoal_permitted;
	String count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getWeekday_start_time() {
		return weekday_start_time;
	}

	public void setWeekday_start_time(String weekday_start_time) {
		this.weekday_start_time = weekday_start_time;
	}

	public String getWeekday_end_time() {
		return weekday_end_time;
	}

	public void setWeekday_end_time(String weekday_end_time) {
		this.weekday_end_time = weekday_end_time;
	}

	public String getWeekend_start_time() {
		return weekend_start_time;
	}

	public void setWeekend_start_time(String weekend_start_time) {
		this.weekend_start_time = weekend_start_time;
	}

	public String getWeekend_end_time() {
		return weekend_end_time;
	}

	public void setWeekend_end_time(String weekend_end_time) {
		this.weekend_end_time = weekend_end_time;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getIs_alchoal_permitted() {
		return is_alchoal_permitted;
	}

	public void setIs_alchoal_permitted(String is_alchoal_permitted) {
		this.is_alchoal_permitted = is_alchoal_permitted;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}

class FestivalInfos {

	String id;
	int area_id;
	String fName;
	String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getArea_id() {
		return area_id;
	}

	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

class Population {
	int id;
	String address;
	int number;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}

public class PalMaeGimBap {

	public static List<List<String>> readToList(String path) {
		List<List<String>> list = new ArrayList<List<String>>();
		File csv = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
			Charset.forName("UTF-8");
			String line = "";

			while ((line = br.readLine()) != null) {
				String[] token = line.split(",");
				List<String> tempList = new ArrayList<String>(Arrays.asList(token));
				list.add(tempList);
			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
			}
		}
		return list;
	}

	private static void insertPopulation(Statement st) throws SQLException {
		List<List<String>> poplist = readToList(
				"C:/Users/Hong Sumin/Documents/카카오톡 받은 파일/population2.csv");
		//System.out.println(poplist.size());
		for (int i = 1; i < poplist.size(); i++) {
			String insertPopulation = "INSERT INTO POPULATION VALUES(default"+", "
					+ Integer.toString(i) + "," + poplist.get(i).get(1) + ");";
			
			st.executeUpdate(insertPopulation);
		}
	}

	private static void insertRegion(Statement st) throws SQLException {
		String[] ls = { "서울특별시 종로구", "서울특별시 중구", "서울특별시 용산구", "서울특별시 성동구", "서울특별시 광진구", "서울특별시 동대문구", "서울특별시 중랑구",
				"서울특별시 성북구", "서울특별시 강북구", "서울특별시 도봉구", "서울특별시 노원구", "서울특별시 은평구", "서울특별시 서대문구", "서울특별시 마포구", "서울특별시 양천구",
				"서울특별시 강서구", "서울특별시 구로구", "서울특별시 금천구", "서울특별시 영등포구", "서울특별시 동작구", "서울특별시 관악구", "서울특별시 서초구", "서울특별시 강남구",
				"서울특별시 송파구", "서울특별시 강동구", "부산광역시 중구", "부산광역시 서구", "부산광역시 동구", "부산광역시 영도구", "부산광역시 부산진구", "부산광역시 동래구",
				"부산광역시 남구", "부산광역시 북구", "부산광역시 해운대구", "부산광역시 사하구", "부산광역시 금정구", "부산광역시 강서구", "부산광역시 연제구", "부산광역시 수영구",
				"부산광역시 사상구", "부산광역시 기장군", "대구광역시 중구", "대구광역시 동구", "대구광역시 서구", "대구광역시 남구", "대구광역시 북구", "대구광역시 수성구",
				"대구광역시 달서구", "대구광역시 달성군", "인천광역시 중구", "인천광역시 동구", "인천광역시 미추홀구", "인천광역시 연수구", "인천광역시 남동구", "인천광역시 부평구",
				"인천광역시 계양구", "인천광역시 서구", "인천광역시 강화군", "인천광역시 옹진군", "광주광역시 동구", "광주광역시 서구", "광주광역시 남구", "광주광역시 북구",
				"광주광역시 광산구", "대전광역시 동구", "대전광역시 중구", "대전광역시 서구", "대전광역시 유성구", "대전광역시 대덕구", "울산광역시 중구", "울산광역시 남구",
				"울산광역시 동구", "울산광역시 북구", "울산광역시 울주군", "세종시", "경기도 수원시 장안구", "경기도 수원시 권선구", "경기도 수원시 팔달구", "경기도 수원시 영통구",
				"경기도 성남시 수정구", "경기도 성남시 중원구", "경기도 성남시 분당구", "경기도 의정부시", "경기도 안양시 만안구", "경기도 안양시 동안구", "경기도 부천시",
				"경기도 광명시", "경기도 평택시", "경기도 동두천시", "경기도 안산시 상록구", "경기도 안산시 단원구", "경기도 고양시 덕양구", "경기도 고양시 일산동구",
				"경기도 고양시 일산서구", "경기도 과천시", "경기도 구리시", "경기도 남양주시", "경기도 오산시", "경기도 시흥시", "경기도 군포시", "경기도 의왕시", "경기도 하남시",
				"경기도 용인시 처인구", "경기도 용인시 기흥구", "경기도 용인시 수지구", "경기도 파주시", "경기도 이천시", "경기도 안성시", "경기도 김포시", "경기도 화성시",
				"경기도 광주시", "경기도 양주시", "경기도 포천시", "경기도 여주시", "경기도 연천군", "경기도 가평군", "경기도 양평군", "강원도 춘천시", "강원도 원주시",
				"강원도 강릉시", "강원도 동해시", "강원도 태백시", "강원도 속초시", "강원도 삼척시", "강원도 홍천군", "강원도 횡성군", "강원도 영월군", "강원도 평창군",
				"강원도 정선군", "강원도 철원군", "강원도 화천군", "강원도 양구군", "강원도 인제군", "강원도 고성군", "강원도 양양군", "충청북도 청주시 서원구",
				"충청북도 청주시 청원구", "충청북도 청주시 상당구", "충청북도 청주시 흥덕구", "충청북도 충주시", "충청북도 제천시", "충청북도 보은군", "충청북도 옥천군",
				"충청북도 영동군", "충청북도 증평군", "충청북도 진천군", "충청북도 괴산군", "충청북도 음성군", "충청북도 단양군", "충청남도 당진시", "충청남도 천안시 동남구",
				"충청남도 천안시 서북구", "충청남도 공주시", "충청남도 보령시", "충청남도 아산시", "충청남도 서산시", "충청남도 논산시", "충청남도 계룡시", "충청남도 금산군",
				"충청남도 부여군", "충청남도 서천군", "충청남도 청양군", "충청남도 홍성군", "충청남도 예산군", "충청남도 태안군", "전라북도 전주시 완산구", "전라북도 전주시 덕진구",
				"전라북도 군산시", "전라북도 익산시", "전라북도 정읍시", "전라북도 남원시", "전라북도 김제시", "전라북도 완주군", "전라북도 진안군", "전라북도 무주군",
				"전라북도 장수군", "전라북도 임실군", "전라북도 순창군", "전라북도 고창군", "전라북도 부안군", "전라남도 목포시", "전라남도 여수시", "전라남도 순천시",
				"전라남도 나주시", "전라남도 광양시", "전라남도 담양군", "전라남도 곡성군", "전라남도 구례군", "전라남도 고흥군", "전라남도 보성군", "전라남도 화순군",
				"전라남도 장흥군", "전라남도 강진군", "전라남도 해남군", "전라남도 영암군", "전라남도 무안군", "전라남도 함평군", "전라남도 영광군", "전라남도 장성군",
				"전라남도 완도군", "전라남도 진도군", "전라남도 신안군", "경상북도 포항시 남구", "경상북도 포항시 북구", "경상북도 경주시", "경상북도 김천시", "경상북도 안동시",
				"경상북도 구미시", "경상북도 영주시", "경상북도 영천시", "경상북도 상주시", "경상북도 문경시", "경상북도 경산시", "경상북도 군위군", "경상북도 의성군",
				"경상북도 청송군", "경상북도 영양군", "경상북도 영덕군", "경상북도 청도군", "경상북도 고령군", "경상북도 성주군", "경상북도 칠곡군", "경상북도 예천군",
				"경상북도 봉화군", "경상북도 울진군", "경상북도 울릉군", "경상남도 창원시 의창구", "경상남도 창원시 성산구", "경상남도 창원시 마산합포구", "경상남도 창원시 마산회원구",
				"경상남도 창원시 진해구", "경상남도 진주시", "경상남도 통영시", "경상남도 사천시", "경상남도 김해시", "경상남도 밀양시", "경상남도 거제시", "경상남도 양산시",
				"경상남도 의령군", "경상남도 함안군", "경상남도 창녕군", "경상남도 고성군", "경상남도 남해군", "경상남도 하동군", "경상남도 산청군", "경상남도 함양군",
				"경상남도 거창군", "경상남도 합천군", "제주도 제주시", "제주도 서귀포시" };

		for (int i = 0; i < ls.length; i++) {
			String insertRegion = "INSERT INTO REGION VALUES(default" + ", \'" + ls[i] + "\');";
			st.executeUpdate(insertRegion);
		}

	}

	private static void insertFoodTruckStatus(Statement st) throws SQLException {
		List<List<String>> foodTruckStatusList = readToList(
				"C:/Users/Hong Sumin/Documents/카카오톡 받은 파일/foodTruckStatus.csv");
		//System.out.println(foodTruckStatusList.size());
		int pkNum = 1;
		for (int i = 1; i < foodTruckStatusList.size(); i++) {

			String[] jibun = foodTruckStatusList.get(i).get(10).split(" ");
			if (jibun.length > 2) {
				String s = "SELECT * FROM REGION WHERE region like \'%" + jibun[2] + "%\' limit 1";
				ResultSet foodTruckStatusSet = st.executeQuery(s);
				if (foodTruckStatusSet.next() == false) {
					s = "SELECT * FROM REGION WHERE region like \'%" + jibun[1] + "%\'";
					foodTruckStatusSet = st.executeQuery(s);
					while (foodTruckStatusSet.next()) {
						String inserFoodTruckStatus = "INSERT INTO FOODTRUCK_STATUS VALUES(default"
						+ "," + foodTruckStatusSet.getString(1) + ",\'" + foodTruckStatusList.get(i).get(1)
						+ "\'" // 사업장 명
						+ ",\'" + foodTruckStatusList.get(i).get(3) + "\'" // 영업상태명
						+ ",\'" + foodTruckStatusList.get(i).get(9) + "\'" // 소재지도로명
						+ ",\'" + foodTruckStatusList.get(i).get(10) + "\'" // 소재지지번주소
						+ ");";
						st.executeUpdate(inserFoodTruckStatus);
						pkNum++;
						break;
					}
				} else {
					while (foodTruckStatusSet.next()) {
						// System.out.println(foodTruckStatusSet.getString(1));
						
						String inserFoodTruckStatus = "INSERT INTO FOODTRUCK_STATUS VALUES(default"
						+ "," + foodTruckStatusSet.getString(1) + ",\'" + foodTruckStatusList.get(i).get(1)
						+ "\'" // 사업장 명
						+ ",\'" + foodTruckStatusList.get(i).get(3) + "\'" // 영업상태명
						+ ",\'" + foodTruckStatusList.get(i).get(9) + "\'" // 소재지도로명
						+ ",\'" + foodTruckStatusList.get(i).get(10) + "\'" // 소재지지번주소
						+ ");";

						st.executeUpdate(inserFoodTruckStatus);
						pkNum++;
						break;
					}
				}
			}
		}
	}

	private static void insertArea(Statement st) throws SQLException, IOException {
		ArrayList<Area> area = returnArea();
		int count = 0;
		for (int i1 = 0; i1 < area.size(); i1++) {
			Area a = area.get(i1);
			String insertArea = "insert into AREAS values(default" + "," + a.getAddress() + "," + a.getStart_date()
			+ "," + a.getEnd_date() + "," + a.getWeekday_start_time() + "," + a.getWeekday_end_time() + ","
			+ a.getWeekend_start_time() + "," + a.getWeekend_end_time() + "," + a.getMoney() + ","
			+ a.getIs_alchoal_permitted() + "," + a.getCount() + ");";
			count++;
			st.executeUpdate(insertArea);
		}
	}
	
	private static void insertPopulationRollup(Statement st) throws SQLException, IOException{
		String insertPopulationRollup = "insert into POPULATION_ROLLUP\n" + 
				"SELECT *\n" + 
				"FROM (SELECT \n" + 
				"		  CASE\n" + 
				"		  	WHEN DISTRICT IS NULL THEN '전체'\n" + 
				"		  ELSE DISTRICT\n" + 
				"	  		END AS 도,\n" + 
				"		  CASE\n" + 
				"		  WHEN CITY IS NULL THEN '전체'\n" + 
				"		  ELSE CITY\n" + 
				"		  	END AS 시,\n" + 
				"		  CASE\n" + 
				"		  WHEN GU IS NULL\n" + 
				"		  OR GU LIKE '' THEN '전체'\n" + 
				"		  ELSE GU\n" + 
				"		  END AS 군구 \n" + 
				"	  ,POPULATIONS AS 인구수\n" + 
				"		FROM\n" + 
				"			(SELECT SPLIT_PART(REGION.REGION,' ',1) DISTRICT,\n" + 
				"					SPLIT_PART(REGION.REGION,' ',2) CITY,\n" + 
				"					SPLIT_PART(REGION.REGION,' ',3) GU,\n" + 
				"					SUM(POPULATION.POPULATION) AS POPULATIONS\n" + 
				"				FROM POPULATION\n" + 
				"				JOIN REGION ON POPULATION.REGION_ID = REGION.ID\n" + 
				"				GROUP BY ROLLUP(DISTRICT, CITY, GU)\n" + 
				"				ORDER BY DISTRICT,\n" + 
				"					CITY,\n" + 
				"					GU) AS ROLLUPS) AS V\n" + 
				"GROUP BY (V.도,V.시,V.군구,V.인구수)\n" + 
				"ORDER BY (V.도,V.시);";
		st.executeUpdate(insertPopulationRollup);
	}

	public static JSONArray apiResultFestival(String pageNum) throws IOException {

		StringBuilder urlBuilder = new StringBuilder(
				"http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=nSnqFcguhENL5E9%2FXcNr%2BQiHy7hCNrE3b9Lfwh8%2FFNWGywBci1YIrcc4Lo92FjY3q5SeP5L4Njq1DuWH3dM08w%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNum, "UTF-8")); /* 페이지 번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* XML/JSON 여부 */


		URL url1 = new URL(urlBuilder.toString());
		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
		conn1.setRequestMethod("GET");
		conn1.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn1.getResponseCode() >= 200 && conn1.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn1.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		rd.close();
		conn1.disconnect();

		JSONParser parser = new JSONParser();
		JSONObject obj = null;

		try {
			obj = (JSONObject) parser.parse(sb.toString());
		} catch (ParseException e) {
			System.out.println("변환에 실패");
			e.printStackTrace();
		}

		JSONObject response = (JSONObject) obj.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONArray items = (JSONArray) body.get("items");
		return items;

	}

	private static void insertFestival(Statement st) throws SQLException, IOException {
		JSONArray Fitem = apiResultFestival("0");
		JSONArray Fitem2 = apiResultFestival("1");
		JSONArray Fitem3 = apiResultFestival("2");

		Fitem.addAll(Fitem2);
		Fitem.addAll(Fitem3);

		int f_id = 0;
		ArrayList<FestivalInfos> festivalinfos = new ArrayList<FestivalInfos>();
		for (int i = 0; i < Fitem.size(); i++) {

			JSONObject objectInArray = (JSONObject) Fitem.get(i);
			FestivalInfos tempFestivalInfos = new FestivalInfos();

			// null이나 빈칸인 경우 저장하지 않음
			if (((String) objectInArray.get("rdnmadr")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("rdnmadr")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempFestivalInfos.setAddress("'" + (String) objectInArray.get("rdnmadr") + "'");
			}

			if (((String) objectInArray.get("fstvlNm")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("fstvlNm")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempFestivalInfos.setfName("'" + (String) objectInArray.get("fstvlNm") + "'");
			}

			tempFestivalInfos.setId(Integer.toString(f_id));
			f_id++;
			festivalinfos.add(tempFestivalInfos);
		}

		// area insert
		for (FestivalInfos a : festivalinfos) {
			String insertFestivalInfos = "insert into FESTIVAL_INFOS values(default"+ "," + a.getfName() + ","
					+ a.getAddress() + ");";
			st.executeUpdate(insertFestivalInfos);
		}

	}

	// api에서 정보 가져오기
	public static JSONArray apiResultArea(String pageNum) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://api.data.go.kr/openapi/tn_pubr_public_food_truck_permit_area_api"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=%2BOSwyW3ekDuCl8jp63XFAvi8J5oe4nWPL7uDQE9odRF8YcTQrNGv5nbEZc0ihfjijG17KNfF28ywMX%2FWeUXjTA%3D%3D"); /*
																														 * service
																														 * Key
																														 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNum, "UTF-8")); /* 페이지 번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* XML/JSON 여부 */

		URL url1 = new URL(urlBuilder.toString());
		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
		conn1.setRequestMethod("GET");
		conn1.setRequestProperty("Content-type", "application/json");

		BufferedReader rd;
		if (conn1.getResponseCode() >= 200 && conn1.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn1.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		rd.close();
		conn1.disconnect();

		JSONParser parser = new JSONParser();
		JSONObject obj = null;

		try {
			obj = (JSONObject) parser.parse(sb.toString());
		} catch (ParseException e) {
			System.out.println("변환에 실패");
			e.printStackTrace();
		}

		JSONObject response = (JSONObject) obj.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONArray items = (JSONArray) body.get("items");

		return items;

	}

	public static ArrayList<Area> returnArea() throws IOException {
		JSONArray items = apiResultArea("0");
		JSONArray items2 = apiResultArea("1");
		JSONArray items3 = apiResultArea("2");
		JSONArray items4 = apiResultArea("3");

		items.addAll(items2);
		items.addAll(items3);
		items.addAll(items4);

		ArrayList<Area> area = new ArrayList<Area>();
		int area_id = 0;
		for (int i = 0; i < items.size(); i++) {
			JSONObject objectInArray = (JSONObject) items.get(i);
			Area tempArea = new Area();

			// null이나 빈칸인 경우 저장하지 않음
			if (((String) objectInArray.get("lnmadr")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("lnmadr")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempArea.setAddress("'" + (String) objectInArray.get("lnmadr") + "'");
			}

			if (((String) objectInArray.get("beginDate")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("beginDate")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempArea.setStart_date("'" + (String) objectInArray.get("beginDate") + "'");
			}

			if (((String) objectInArray.get("endDate")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("endDate")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempArea.setEnd_date("'" + (String) objectInArray.get("endDate") + "'");
			}

			if (((String) objectInArray.get("weekdayOperOpenHhmm")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("weekdayOperOpenHhmm")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempArea.setWeekday_start_time("'" + (String) objectInArray.get("weekdayOperOpenHhmm") + "'");
			}

			if (((String) objectInArray.get("weekdayOperColseHhmm")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("weekdayOperColseHhmm")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempArea.setWeekday_end_time("'" + (String) objectInArray.get("weekdayOperColseHhmm") + "'");
			}

			if (((String) objectInArray.get("wkendOperOpenHhmm")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("wkendOperOpenHhmm")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempArea.setWeekend_start_time("'" + (String) objectInArray.get("wkendOperOpenHhmm") + "'");
			}

			if (((String) objectInArray.get("wkendOperCloseHhmm")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("wkendOperCloseHhmm")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempArea.setWeekend_end_time("'" + (String) objectInArray.get("wkendOperCloseHhmm") + "'");
			}

			if (((String) objectInArray.get("prmisnZoneRntfee")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("prmisnZoneRntfee")).equalsIgnoreCase("null")) {
				continue;
			} else {
				String tempMoney = (String) objectInArray.get("prmisnZoneRntfee");

				String intTempMoney = tempMoney.replaceAll("[^0-9]", "");
				if (intTempMoney.equalsIgnoreCase("")) {
					tempArea.setMoney("'" + "0" + "'");
				} else {
					tempArea.setMoney("'" + tempMoney + "'");
				}
			}

			String tempAlchol = (String) objectInArray.get("lmttPrdlst");
			if (tempAlchol.equalsIgnoreCase("주류") || tempAlchol.equalsIgnoreCase("술")) {
				tempArea.setIs_alchoal_permitted("true");
			} else {
				tempArea.setIs_alchoal_permitted("false");
			}

			if (((String) objectInArray.get("vhcleCo")).equalsIgnoreCase("")
					|| ((String) objectInArray.get("vhcleCo")).equalsIgnoreCase("null")) {
				continue;
			} else {
				tempArea.setCount((String) objectInArray.get("vhcleCo"));
			}

			tempArea.setId(Integer.toString(area_id));
			area_id++;
			area.add(tempArea);

		}
		return area;
	}

	public static void main(String[] args) throws SQLException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement st = null;
		ResultSet r = null;
		PreparedStatement p = null;

		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Connecting PostgreSQL database");
			// JDBC를 이용해 PostgreSQL 서버 및 데이터베이스 연결

			String url = "jdbc:postgresql://localhost/dbproject3";
			String user = "postgres";
			String password = "sumin8411*";

			conn = DriverManager.getConnection(url, user, password);

			st = conn.createStatement();
			// 초기화
			st.execute("DROP TRIGGER IF EXISTS FOODTRUCKSTATUS_INSERT_TRIGGER ON FOODTRUCK_STATUS RESTRICT");
			st.execute("DROP function IF EXISTS INSERT_FOODTRUCK() RESTRICT");
			st.execute("DROP VIEW IF EXISTS OPENING RESTRICT");
			st.execute("DROP TABLE IF EXISTS FESTIVAL_INFOS RESTRICT");
			st.execute("DROP TABLE IF EXISTS AREAS RESTRICT");
			st.execute("DROP TABLE IF EXISTS POPULATION RESTRICT");
			st.execute("DROP TABLE IF EXISTS FOODTRUCK_STATUS RESTRICT");
			st.execute("DROP TABLE IF EXISTS REGION RESTRICT");
			st.execute("DROP TABLE IF EXISTS POPULATION_ROLLUP RESTRICT");

			System.out.println("Creating AREAS, FESTIVAL_INFOS,POPULATION, FOODTRUCK_STATUS,REGION,POPULATION_ROLLUP relations");
			// 테이블 생성 : Create table 문 이용
			String AREAS = "Create Table AREAS(id SERIAL PRIMARY KEY, address VARCHAR(50),start_date VARCHAR(10), end_date VARCHAR(10), "
					+ "weekday_start_time VARCHAR(10), weekday_end_time VARCHAR(10),"
					+ "weekend_start_time VARCHAR(10), weekend_end_time VARCHAR(10),"
					+ "money VARCHAR(50), is_alcohol_permitted boolean, count int)";

			st.executeUpdate(AREAS);
			String FESTIVAL_INFO = "Create Table FESTIVAL_INFOS(id SERIAL PRIMARY KEY,fName VARCHAR(50), address VARCHAR(50))";
			st.executeUpdate(FESTIVAL_INFO);
			String REGION = "Create Table REGION(id SERIAL PRIMARY KEY, region VARCHAR(50));";
			st.executeUpdate(REGION);
			String POPULATION = "Create Table POPULATION(id SERIAL PRIMARY KEY, region_id INT REFERENCES REGION(id) on delete cascade, population int)";
			st.executeUpdate(POPULATION);
			String FOODTRUCK_STATUS = "Create Table FOODTRUCK_STATUS(id SERIAL PRIMARY KEY, region_id INT REFERENCES REGION(id) on delete set null,"
					+ " name VARCHAR(50), status VARCHAR(20), street_name_address VARCHAR(100), lot_address VARCHAR(100));";
			st.executeUpdate(FOODTRUCK_STATUS);
			String POPULATION_ROLLUP = "Create Table POPULATION_ROLLUP(도 varchar(10),시 varchar(10), 군구 varchar(10), 인구수 int);";
			st.executeUpdate(POPULATION_ROLLUP);
			
			insertArea(st);
			insertFestival(st);
			insertRegion(st);
			insertFoodTruckStatus(st);
			insertPopulation(st);
			insertPopulationRollup(st);
			
			String OPENING_VIEW = "create view opening as\n"
					+ "select id,region_id,name,street_name_address,lot_address\n" + "from FOODTRUCK_STATUS\n"
					+ "where status = '영업';";
			st.executeUpdate(OPENING_VIEW);

			String CREATE_FUNCTION = "CREATE OR REPLACE FUNCTION INSERT_FOODTRUCK() RETURNS TRIGGER AS $$\n" + "BEGIN\n"
					+ "    update foodtruck_status set region_id = COALESCE((select id from region where region.region\n"
					+ "											 like concat('%',split_part(foodtruck_status.lot_address,' ',3),'%') limit 1)\n"
					+ "													,(select id from region where region.region\n"
					+ "											 like concat('%',split_part(foodtruck_status.lot_address,' ',2),'%') limit 1)) ;\n"
					+ "	RETURN new;\n" + "END;\n" + "$$ LANGUAGE 'plpgsql';";
			st.executeUpdate(CREATE_FUNCTION);

			String CREATE_TRIGGER = "CREATE TRIGGER FOODTRUCKSTATUS_INSERT_TRIGGER AFTER\n"
					+ "INSERT ON \"foodtruck_status\" EXECUTE PROCEDURE INSERT_FOODTRUCK();";

			st.executeUpdate(CREATE_TRIGGER);
			System.out.println("*************************************************************************");
			System.out.println("*\t\t\t\t\t\t\t\t\t*");
			System.out.println("*\t\t\t팔매김밥조 장지석 고예준 홍수민\t\t\t\t*");
			System.out.println("*\t\t\t\t\t\t\t\t\t*");
			System.out.println("*\t\t\t푸드트럭 허가구역 정보 제공 서비스\t\t\t*");
			System.out.println("*\t\t\t\t\t\t\t\t\t*");
			System.out.println("*************************************************************************");

			while (true) {
				System.out.println();
				System.out.println("*************************************************************************");
				System.out.println();

				System.out.println("원하는 버전을 선택하시오.");
				System.out.println("1)고객 버전");
				System.out.println("2)관리자 버전");
				
				System.out.println("---------------------------------------------------------------------");
				int version = scan.nextInt();

				if (version == 1) {
					System.out.println("고객 버전을 선택하였습니다.");

					System.out.println("[menu]");
					System.out.println("0)종료");
					System.out.println("1)푸드트럭 장소 검색");
					System.out.println("2)전국 축제데이터 검색");
					System.out.println("3)푸드트럭 현황 조회");
					System.out.println("4)지역별 인구수 조회");
					System.out.print("입력 : ");
					int menu = scan.nextInt();
					System.out.println();

					if (menu == 0) {
						break;
					} else if (menu == 1) {
						ResultSet r2 = null;
						PreparedStatement p2 = null;
						ResultSet r3 = null;
						PreparedStatement p3 = null;

						Scanner scan2 = new Scanner(System.in);
						System.out.println("푸드트럭 장소검색입니다.");
						System.out.print("검색할 장소를 입력하세요(광역시, 도):");
						String cityName1 = scan2.nextLine();
						System.out.println(cityName1 + " 을(를) 입력하였습니다.");
						System.out.println();
						System.out.print("검색할 장소를 입력하세요(구,시 ):");
						String cityName2 = scan2.nextLine();
						System.out.println(cityName2 + " 을(를) 입력하였습니다.");
						System.out.println();
						System.out.print("주류 판매를 하실건가요? (Y/N) :");
						String alcohol = scan2.nextLine();
						System.out.println(alcohol + " 을(를) 입력하였습니다.");
						System.out.println();

						String addressR, feeR, floating_numR, week_start_timeR, week_end_timeR, weekend_start_timeR,
								weekend_end_timeR;
						String festival_nameR, festival_addressR;
						boolean alcoholR;

						String queryAdd = "select address, weekday_start_time, weekday_end_time, "
								+ "weekend_start_time, weekend_end_time,money,is_alcohol_permitted" + " from AREAS"
								+ " where is_alcohol_permitted = ? and address like ? || '%' and address like '%' || ? || '%' limit 6;";
						p = conn.prepareStatement(queryAdd);
						p.clearParameters();
						if (alcohol.equalsIgnoreCase("Y")) {
							p.setBoolean(1, true);
						} else {
							p.setBoolean(1, false);
						}
						p.setString(2, cityName1);
						p.setString(3, cityName2);
						r = p.executeQuery();

						String queryFest = "select fName, address" + " from FESTIVAL_INFOS"
								+ " where address like ? || '%' and address like '%' || ? || '%' limit 6;";
						p2 = conn.prepareStatement(queryFest);
						p2.clearParameters();
						p2.setString(1, cityName1);
						p2.setString(2, cityName2);
						r2 = p2.executeQuery();

						int pop = 0;

						String queryPop = "select sum(population) from POPULATION join region on region.id = population.region_id where region.region like '%"
								+ cityName2 + "%'";
						p3 = conn.prepareStatement(queryPop);
						r3 = p3.executeQuery();
						while (r3.next()) {
							pop = r3.getInt(1);
						}

						System.out.println("=====================================================================");
						if (r.next() == false) {
							System.out.println("푸드트럭 허가구역이 없습니다.");
						} else {
							System.out.println("[푸드트럭 허가구역]");
							int cnt = 1;
							do {
								addressR = r.getString(1);
								week_start_timeR = r.getString(2);
								week_end_timeR = r.getString(3);
								weekend_start_timeR = r.getString(4);
								weekend_end_timeR = r.getString(5);
								feeR = r.getString(6);
								alcoholR = r.getBoolean(7);

								System.out.println();
								System.out.printf("%d 번\n", cnt);
								System.out.println("푸드트럭 존 주소이름 :" + addressR);
								System.out.println("사용료 :" + feeR);
								System.out.println("푸드트럭 영업 가능 시간(평일):" + week_start_timeR + "-" + week_end_timeR);
								System.out
										.println("푸드트럭 영업 가능 시간(주말):" + weekend_start_timeR + "-" + weekend_end_timeR);
								if (alcoholR == true) {
									System.out.println("주류 판매 가능 여부 : Y");
								} else {
									System.out.println("주류 판매 가능 여부 : N");
								}
								cnt++;
							} while (r.next());
						}

						System.out.println();

						System.out.println("---------------------------------------------------------------------");

						if (r2.next() == false) {
							System.out.println("문화 축제 정보가 없습니다.");
						} else {

							System.out.println("[문화 축제 정보]");
							do {
								festival_nameR = r2.getString(1);
								festival_addressR = r2.getString(2);
								System.out.println("축제 이름 : " + festival_nameR);
								System.out.println("주소 : " + festival_addressR);
								System.out.println();
							} while (r2.next());
						}

						System.out.println("——————————————————————————————————");
						System.out.println(cityName1 + " " + cityName2 + " 인구 수 : " + Integer.toString(pop) + "명");
						System.out.println("=====================================================================");
						System.out.println();

					}

					else if (menu == 2) {
						System.out.println("전국 축제 데이터 검색입니다.");
						System.out.print("검색할 장소를 입력하세요. : ");
						Scanner scan2 = new Scanner(System.in);
						String location = scan2.nextLine();

						// 도
						System.out.println();
						System.out.println(location + "을(를) 입력하였습니다.");

						System.out.print("축제 이름을 입력하시오. : ");
						Scanner scan3 = new Scanner(System.in);
						String festival_name = scan3.nextLine();

						p = conn.prepareStatement(
								"select distinct fname,address from FESTIVAL_INFOS where address like '%" + location
										+ "%' and fname like '%" + festival_name + "%'");
						r = p.executeQuery();

						ResultSetMetaData rsmd;
						rsmd = r.getMetaData();

						System.out.println();
						int col3 = rsmd.getColumnCount();
						System.out.println("=====================================================================");
						int f_cnt = 0;
						System.out.println("[문화 축제 정보]");
						System.out.println();
						while (r.next()) {
							f_cnt++;
							for (int i = 1; i <= col3; i++) {
								if (i == 1) {
									System.out.print("축제이름 : ");
								}

								if (i == 2) {
									System.out.print("주소 : ");
								}
								System.out.println(r.getObject(i) + " ");

							}
							System.out.println();
						}
						if (f_cnt == 0) {
							System.out.println("문화 축제 데이터가 없습니다.");
						}

						System.out.println("---------------------------------------------------------------------");

						p = conn.prepareStatement("select address from AREAS where address like '%" + location + "%'");
						r = p.executeQuery();

						rsmd = r.getMetaData();

						int col4 = rsmd.getColumnCount();
						int fd_cnt = 0;
						System.out.println("[푸드트럭 허가구역]");
						System.out.println();
						while (r.next()) {
							fd_cnt++;
							for (int i = 1; i <= col4; i++) {
								System.out.print("푸드트럭 허가구역 주소 : ");
								System.out.println(r.getObject(i) + " ");
							}
						}
						if (fd_cnt == 0) {
							System.out.println("푸드트럭 허가구역이 없습니다.");
						}

						System.out.println("---------------------------------------------------------------------");

						p = conn.prepareStatement(
								"select sum(population) from population join region on region.id = population.region_id where region.region like '%"
										+ location + "%'");
						r = p.executeQuery();

						rsmd = r.getMetaData();

						int col5 = rsmd.getColumnCount();
						System.out.println();
						while (r.next()) {
							for (int i = 1; i <= col5; i++) {
								System.out.print(location + "인구수 : ");
								System.out.println(r.getObject(i) + " ");
							}
						}

						System.out.println("=====================================================================");

					} else if (menu == 3) {
						System.out.println("영업중인 푸드트럭 현황 조회입니다.");
						Scanner scan2 = new Scanner(System.in);
						System.out.print("검색할 장소를 입력하세요(도):");
						String cityName1 = scan2.nextLine();
						System.out.println(cityName1 + " 을(를) 입력하였습니다.");
						System.out.println();
						System.out.print("검색할 장소를 입력하세요(시,군,구):");
						String cityName2 = scan2.nextLine();
						System.out.println(cityName2 + " 을(를) 입력하였습니다.");

						String name_R, lot_address_R;

						String queryAdd = "select name,lot_address" + " from opening"
								+ " where lot_address like ? || '%' and lot_address like '%' || ? || '%' limit 6;";
						p = conn.prepareStatement(queryAdd);
						p.clearParameters();
						p.setString(1, cityName1);
						p.setString(2, cityName2);
						r = p.executeQuery();

						System.out.println("=====================================================================");
						if (r.next() == false) {
							System.out.println("해당 지역에 운영중인 푸드트럭이 없습니다.");
						} else {
							System.out.printf("[(%s %s)의 운영중인 푸드트럭]\n", cityName1, cityName2);
							do {
								name_R = r.getString(1);
								lot_address_R = r.getString(2);

								System.out.println();
								System.out.println("푸드트럭 이름 : " + name_R);
								System.out.println("운영 장소 :" + lot_address_R);

							} while (r.next());
						}

						System.out.println();

						System.out.println("=====================================================================");
						System.out.println();

					} else if (menu == 4) {
						System.out.println("지역별 인구수 조회 모드를 선택하셨습니다.");
						System.out.println("인구수를 조회하고 싶은 지역(도 or 시 or 군/구)을 입력하세요.");
						Scanner scan2 = new Scanner(System.in);
						String city = scan2.nextLine();

						String query="select * from POPULATION_ROLLUP \n" + 
								"where 도 like '%' || ? || '%' or 시 like '%' || ? || '%' "
								+ "or 군구 like '%' || ? || '%';";
						p = conn.prepareStatement(query);
						p.clearParameters();
						p.setString(1, city);
						p.setString(2, city);
						p.setString(3, city);
						r = p.executeQuery();

						System.out.println();

						ResultSetMetaData rsmd;
						rsmd = r.getMetaData();

						System.out.printf("[%s 지역 인구수]\n",city);
						int p_col = rsmd.getColumnCount();
						while (r.next()) {

							for (int i = 1; i <= p_col; i++) {
								System.out.print(r.getObject(i) + " ");
							}
							System.out.println();
						}

					}

				}

				else if (version == 2) {
					System.out.println("---------------------------------------------------------------------");
					System.out.println("관리자 버전을 선택하였습니다.");
					System.out.println("원하는 번호를 입력하시오.");
					System.out.println("1)푸드트럭 현황 관리");
					System.out.println("2)문화축제 정보 관리");
					System.out.println("3)지역정보 관리");

					int adminstate = scan.nextInt();

					if (adminstate == 1) {
						System.out.println("푸드트럭 현황 추가/삭제/수정하기 모드입니다. \n");

						System.out.println("---------------------------------------------------------------------");
						System.out.println("다음은 푸드트럭 현황 정보입니다. ");

						p = conn.prepareStatement("select * from FOODTRUCK_STATUS");
						r = p.executeQuery();

						int pid, region_id;
						String name, status = "", street_name_address, lot_address;

						

						while (r.next()) {
							pid = r.getInt(1);
							region_id = r.getInt(2);
							name = r.getString(3);
							status = r.getString(4);
							street_name_address = r.getString(5);
							lot_address = r.getString(6);

							System.out.printf("|%-4d|%-4d|%-35s|%-5s|%-40s|%-40s|\n", pid, region_id, name, status,
									street_name_address, lot_address);
						}

						System.out.println("\n1) 푸드트럭 현황 추가하기");
						System.out.println("2) 푸드트럭 현황 삭제하기 ");
						System.out.println("3) 푸드트럭 현황 수정하기 ");
						System.out.println("4) 첫 화면으로 돌아가기 ");
						System.out.print("입력 : ");

						int crud = scan.nextInt();
						System.out.println();

						if (crud == 1) {
		                     Scanner scan5 = new Scanner(System.in);
		                     System.out.println("푸드트럭 현황 추가하기 모드입니다.");
		                     System.out.println("푸드트럭 이름을 입력하세요 ");
		                     String ft_name = scan5.nextLine();
		                     System.out.println("푸드트럭 영업 현황을 입력하세요");
		                     String ft_state = scan5.nextLine();
		                     
		                     System.out.println("지역을 입력하세요(도 시 군/구를 모두 입력하세요)");
		                     String ft_re = scan5.nextLine();
		                     
		                     int ft_rid=0;
		                     p = conn.prepareStatement("select id from REGION where region='"+ft_re+"'");
		                     r=p.executeQuery();
		                     
		                     while(r.next()) {
		                        ft_rid=r.getInt(1);
		                     }
//		                    System.out.println(ft_re);
//		                     System.out.println(ft_rid);
		                     
		                     
		                     System.out.println("주소를 입력하세요");
		                     String ft_add=scan5.nextLine();
//		                     
		                     
		                              
		                           st.executeUpdate("insert into foodtruck_status values(default,"+Integer.toString(ft_rid)+",'"+ft_name+"','"+ft_state+"','"+ft_add+"','"+ft_add+"')");
		                            //p.executeUpdate();
		                     
		                     

		                  } else if (crud == 2) {
							System.out.println("푸드트럭 현황 삭제모드입니다.");
							System.out.println("삭제할 푸드트럭 이름을 기입하시오.");
							System.out.println();
							System.out.println("푸드트럭 이름 :");
							Scanner scan3 = new Scanner(System.in);
							String fst_delete_name = scan3.nextLine();

							st.executeUpdate("delete from FOODTRUCK_STATUS where name='" + fst_delete_name + "'");
							
							//System.out.println("delete from FOODTRUCK_STATUS where name='" + fst_delete_name + "'");
							System.out.println("삭제가 완료되었습니다.");
						} else if (crud == 3) {
							System.out.println("푸드트럭 현황 수정모드입니다.");
							System.out.println("수정할 푸드트럭 이름을 입력하세요");
							Scanner scan3 = new Scanner(System.in);
							String foodtruck_name = scan3.nextLine();
							
							System.out.println("\n현재 입력한 푸드트럭의 상황입니다.");
							String query = "select * from FOODTRUCK_STATUS where name='"+foodtruck_name+"';";
							p = conn.prepareStatement(query);
							r = p.executeQuery();
							
							while (r.next()) {
								pid = r.getInt(1);
								region_id = r.getInt(2);
								name = r.getString(3);
								status = r.getString(4);
								street_name_address = r.getString(5);
								lot_address = r.getString(6);

								System.out.printf("|%-4d|%-4d|%-20s|%-20s|%-20s\n", pid, region_id, name, status,
										street_name_address, lot_address);
							}
														
							System.out.println("영업 상태를 수정하시겠습니까?(Y/N)");
							String yesOrNo = scan3.nextLine();
							
							if(yesOrNo.equalsIgnoreCase("Y")) {
								if(status.equalsIgnoreCase("영업")) {
									String query2 = "update FOODTRUCK_STATUS"
											+ " set status='폐업'"
											+ " where name='"+foodtruck_name+"';";
									
									st.executeUpdate(query2);
									
									System.out.println("수정이 완료되었습니다.");
									
									
								}else {
									String query2 = "update FOODTRUCK_STATUS"
											+ " set status='영업'"
											+ " where name='"+foodtruck_name+"';";
									
									st.executeUpdate(query2);
									
									System.out.println("수정이 완료되었습니다.");
								}
							}else {
								System.out.println("관리자 모드를 종료합니다. ");
								continue;
							}
							
						} else {
							continue;
						}

					} else if (adminstate == 2) {
						System.out.println("문화축제 정보 관리를 선택하셨습니다.");
						p = conn.prepareStatement("select * from FESTIVAL_INFOS");
						r = p.executeQuery();

						ResultSetMetaData rsmd;
						rsmd = r.getMetaData();

						System.out.println();
						int cu_col = rsmd.getColumnCount();
						System.out.println("=====================================================================");

						System.out.println("[문화 축제 정보]");
						System.out.println();
						int fcnt = -1;
						while (r.next()) {
							fcnt++;
							for (int i = 1; i <= cu_col; i++) {
								if (i == 2) {
									System.out.print("축제이름 : ");
								}

								if (i == 3) {
									System.out.print("주소 : ");
								}
								System.out.println(r.getObject(i) + " ");

							}
							System.out.println();
						}
						fcnt++;
						System.out.println("=====================================================================");
						System.out.println("1)문화축제 정보 추가");
						System.out.println("2)문화축제 정보 삭제");

						int ct_infos = scan.nextInt();

						if (ct_infos == 1) {
							System.out.println("문화축제 정보 추가모드 입니다.");
							System.out.println("정보를 기입하시오.");
							System.out.println();
							System.out.println("축제 이름 :");
							Scanner scan9 = new Scanner(System.in);
							String fst_name = scan9.nextLine();

							System.out.println("축제 개최 주소 :");
							String fst_addr = scan9.nextLine();

							st.executeUpdate("insert into FESTIVAL_INFOS values(default,'" + fst_name + "','"
									+ fst_addr + "')");

							// p.executeUpdate();

						} else if (ct_infos == 2) {
							System.out.println("문화축제 정보 삭제모드입니다.");
							System.out.println("삭제할 문화축제 이름을 기입하시오.");
							System.out.println();
							System.out.println("축제 이름 :");							
							Scanner scan9 = new Scanner(System.in);
							String fst_delete_name = scan9.nextLine();

							st.executeUpdate("delete from FESTIVAL_INFOS where fname='" + fst_delete_name + "'");
							//System.out.println("delete from FESTIVAL_INFOS where fname='" + fst_delete_name + "'");
						}
					} else if (adminstate == 3) {
						System.out.println("지역정보 관리를 선택하셨습니다.");
						p = conn.prepareStatement("select * from REGION");
						r = p.executeQuery();

						ResultSetMetaData rsmd;
						rsmd = r.getMetaData();

						System.out.println();
						int cu_col = rsmd.getColumnCount();
						System.out.println("=====================================================================");

						System.out.println("[지역별 정보]");
						System.out.println();
						int fcnt = -1;
						while (r.next()) {
							fcnt++;
							for (int i = 1; i <= cu_col; i++) {
								if (i == 1) {
									System.out.print("id : ");
								}

								if (i == 2) {
									System.out.print("주소 : ");
								}
								System.out.println(r.getObject(i) + " ");

							}
							System.out.println();
						}
						fcnt++;
						System.out.println("=====================================================================");
						System.out.println("1)지역별 정보 삭제");
						System.out.println("사용할 메뉴를 입력하세요");
						int ct_infos = scan.nextInt();

						if (ct_infos == 1) {
							System.out.println("지역 정보 삭제 모드 입니다.");
							System.out.println("정보를 기입하시오.");
							System.out.println("삭제할 지역 이름 :");
							Scanner scan2 = new Scanner(System.in);
							String del_name = scan2.nextLine();
							System.out.println();
							st.executeUpdate("delete from REGION where region = '" + del_name + "';");

							//System.out.println("delete from REGION where region = '" + del_name + "';");
							// p.executeUpdate();

						}
					}

				}

			}

		} catch (SQLException ex) {
			throw ex;
		} finally {
			// 연결 끊기
			if (r != null)
				try {
					r.close();
				} catch (SQLException ex) {
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException ex) {
				}
			if (p != null)
				try {
					p.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

}