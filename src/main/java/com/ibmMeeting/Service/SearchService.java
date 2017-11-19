package com.ibmMeeting.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.SearchDao;
import com.ibmMeeting.paging.util.PagingBean;

@Service
public class SearchService {

	@Autowired
	SearchDao searchDao;

	/**
	 * 작성자 : 최문정 내용 : 관리자 일반예약 내역 출력
	 * 
	 * @param request
	 * @param searchCont
	 * @param selectOpt
	 * @param page
	 * @param generalSort
	 * @return
	 */
	public HashMap<String, Object> searchResult(HttpServletRequest request,
			String searchCont, String selectOpt, int page, String generalSort) {

		ArrayList<HashMap<String, Object>> searchResult = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> searchInfo = new HashMap<String, Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (request.getParameter("selectSearchOpt") != null) {

			if (selectOpt.equals("all") && generalSort.equals("new")) {

				// '전체'옵션 검색, 최신순 정렬
				int totalCount = searchDao.rownumSelectByAll(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao
						.generalSelectByAllOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("all") && generalSort.equals("old")) {

				// '전체'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao.rownumSelectByAll(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao
						.generalSelectByAllOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("title") && generalSort.equals("new")) {

				// '회의제목' 옵션, 최신순 정렬
				int totalCount = searchDao.rownumSelectByTitle(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao
						.generalSelectByTitleOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("title") && generalSort.equals("old")) {

				// '회의제목' 옵션, 오래된 순 정렬 정렬
				int totalCount = searchDao.rownumSelectByTitle(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao
						.generalSelectByTitleOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("mem_nm") && generalSort.equals("new")) {

				// '예약자 이름' 옵션, 최신 순 정렬
				int totalCount = searchDao.rownumSelectByMemNM(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao
						.generalSelectByMemNMOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("mem_nm") && generalSort.equals("old")) {

				// '예약자 이름' 옵션, 오래된 순 정렬
				int totalCount = searchDao.rownumSelectByMemNM(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao
						.generalSelectByMemNMOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("mem_pn") && generalSort.equals("new")) {

				// 예약자 번호, 최신순 정렬
				int totalCount = searchDao.rownumSelectByMemPN(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao
						.genelralSelectByMemPNOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("mem_pn") && generalSort.equals("old")) {

				// 예약자 번호, 최신순 정렬
				int totalCount = searchDao.rownumSelectByMemPN(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao
						.genelralSelectByMemPNOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			}

		}

		return map;

	}

	/**
	 * 작성자 : 최문정 내용 : 관리자 반복예약 출력
	 * 
	 * @param request
	 * @param searchCont
	 * @param selectOpt
	 * @param page
	 * @param generalSort
	 * @return
	 */
	public HashMap<String, Object> repeatSearchResult(
			HttpServletRequest request, String searchCont, String selectOpt,
			int page, String generalSort) {

		ArrayList<HashMap<String, Object>> repeatSearchResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> searchInfo = new HashMap<String, Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (request.getParameter("selectSearchOpt") != null) {

			if (selectOpt.equals("all") && generalSort.equals("new")) {

				// 반복예약 '전체'옵션 검색, 최신순 정렬
				int totalCount = searchDao.rownumRepeatSelectByAll(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				repeatSearchResult = searchDao
						.repeatSelectByAllOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			} else if (selectOpt.equals("all") && generalSort.equals("old")) {

				// 반복예약 '전체'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao.rownumRepeatSelectByAll(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				repeatSearchResult = searchDao
						.repeatSelectByAllOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			} else if (selectOpt.equals("title") && generalSort.equals("new")) {

				// 반복예약 '회의제목'옵션 검색, 최신순 정렬
				int totalCount = searchDao
						.rownumRepeatSelectByTitle(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				repeatSearchResult = searchDao
						.repeatSelectByTitleOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			} else if (selectOpt.equals("title") && generalSort.equals("old")) {

				// 반복예약 '회의재목'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao
						.rownumRepeatSelectByTitle(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				repeatSearchResult = searchDao
						.repeatSelectByTitleOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			} else if (selectOpt.equals("mem_nm") && generalSort.equals("new")) {

				// 반복예약 '예약자'옵션 검색, 최신순 정렬
				int totalCount = searchDao
						.rownumRepeatSelectByMemNM(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				repeatSearchResult = searchDao
						.repeatSelectByMemNMOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			} else if (selectOpt.equals("mem_nm") && generalSort.equals("old")) {

				// 반복예약 '예약자'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao
						.rownumRepeatSelectByMemNM(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				repeatSearchResult = searchDao
						.repeatSelectByMemNMOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			} else if (selectOpt.equals("mem_pn") && generalSort.equals("new")) {

				// 반복예약 '예약자 핸드폰 번호'옵션 검색, 최신순 정렬
				int totalCount = searchDao
						.rownumRepeatSelectByMemPN(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				repeatSearchResult = searchDao
						.repeatSelectByMemPNOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			} else if (selectOpt.equals("mem_pn") && generalSort.equals("old")) {

				// 반복예약 '예약자 핸드폰 번호'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao
						.rownumRepeatSelectByMemPN(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				repeatSearchResult = searchDao
						.repeatSelectByMemPNOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			}

		}

		return map;
	}

	/**
	 * 작성자 : 최문정 내용 : 반복예약 모달에서 상세예약내역 출력
	 * 
	 * @param repeatNo
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> showReservDetail(int repeatNo) {

		int repeatSeq = repeatNo;

		ArrayList<HashMap<String, Object>> searchDetailResult = new ArrayList<HashMap<String, Object>>();
		searchDetailResult = searchDao.repeatSearchDetailContents(repeatSeq);

		return searchDetailResult;

	}

	/**
	 * 작성자 : 최문정 내용 : 검색어 입력 시 자동완성
	 * 
	 * @param request
	 * @param response
	 */
	public void formAutoComplete(HttpServletRequest request,
			HttpServletResponse response) {

		ArrayList<HashMap<String, Object>> autocompleteList = new ArrayList<HashMap<String, Object>>();

		String selectOption = request.getParameter("selectSearchOpt");
		String inputValue = request.getParameter("value");

		if (request.getParameter("selectSearchOpt") != null) {

			if (selectOption.equals("all")) {
				autocompleteList = searchDao.autocompleteByAllList(inputValue);
			} else if (selectOption.equals("title")) {
				autocompleteList = searchDao
						.autocompleteByTitleList(inputValue);
			} else if (selectOption.equals("mem_nm")) {
				autocompleteList = searchDao
						.autocompleteByMemNMList(inputValue);
			} else if (selectOption.equals("mem_pn")) {
				autocompleteList = searchDao
						.autocompleteByMemPNList(inputValue);
			}

		}

		// ArratList를 JSON에 입력
		JSONArray jarr = new JSONArray();
		try {
			for (int i = 0; i < autocompleteList.size(); i++) {
				JSONObject sOjbt = new JSONObject();

				// 문자열 자르기
				String attr = autocompleteList.get(i).toString();
				String arr1[] = attr.split("=");
				String arr2[] = arr1[1].split("}");

				// JSON에 입력
				sOjbt.put("data", arr2[0]);

				jarr.put(sOjbt);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("JSON변환에러");
		}

		// PrintWriter로 출력
		try {
			PrintWriter result = response.getWriter();
			result.print(jarr);
			result.flush();
			result.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("PrintWriter 에러");
		}

	}

	/**
	 * 작성자 : 최문정 내용 : 검색어 입력 시 자동완성(간편검색용)
	 * 
	 * @param request
	 * @param response
	 */
	public void easyFormAutoComplete(HttpServletRequest request,
			HttpServletResponse response) {

		ArrayList<HashMap<String, Object>> autocompleteList = new ArrayList<HashMap<String, Object>>();

		String selectOption = request.getParameter("selectSearchOpt");
		String inputValue = request.getParameter("value");

		HashMap<String, Object> easyInfo = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date cDate = new Date();
		String today = sdf.format(cDate);

		easyInfo.put("value", inputValue);
		easyInfo.put("today", today);

		if (request.getParameter("selectSearchOpt") != null) {

			if (selectOption.equals("e_all")) {
				autocompleteList = searchDao
						.easyAutocompleteByAllList(easyInfo);
			} else if (selectOption.equals("e_title")) {
				autocompleteList = searchDao.easyAcompleteByTitleList(easyInfo);
			} else if (selectOption.equals("e_mem_nm")) {
				autocompleteList = searchDao
						.easyAutocompleteByMemNMList(easyInfo);
			} else if (selectOption.equals("e_mem_pn")) {
				autocompleteList = searchDao
						.easyAutocompleteByMemPNList(easyInfo);
			}

		}

		// ArratList를 JSON에 입력
		JSONArray jarr = new JSONArray();
		try {
			for (int i = 0; i < autocompleteList.size(); i++) {
				JSONObject sOjbt = new JSONObject();

				// 문자열 자르기
				String attr = autocompleteList.get(i).toString();
				String arr1[] = attr.split("=");
				String arr2[] = arr1[1].split("}");

				// JSON에 입력
				sOjbt.put("data", arr2[0]);

				jarr.put(sOjbt);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("JSON변환에러");
		}

		// PrintWriter로 출력
		try {
			PrintWriter result = response.getWriter();
			result.print(jarr);
			result.flush();
			result.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("PrintWriter 에러");
		}

	}

	/**
	 * 작성자 : 최문정 내용 : 관리자 예약내역, 일반예약 내역 출력, 시작일과 종료일로 검색
	 * 
	 * @param request
	 * @param page
	 * @param sort
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public HashMap<String, Object> searchGeneralHistoryResult(
			HttpServletRequest request, int page, String sort, String sDate,
			String eDate) {

		ArrayList<HashMap<String, Object>> generalHistorySearchResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		// sDate : 시작일, eDate : 종료일
		HashMap<String, Object> dateInfo = new HashMap<String, Object>();
		dateInfo.put("startDate", sDate);
		dateInfo.put("endDate", eDate);

		int totalCount = searchDao.rownumSelectByDate(dateInfo);
		PagingBean pageBean = new PagingBean(totalCount, page);

		// startPage : 페이지 시작 row, endPage : 페이지 종료 row
		HashMap<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("startDate", sDate);
		searchInfo.put("endDate", eDate);
		searchInfo.put("startPage", pageBean.getBeginItemInPage());
		searchInfo.put("endPage", pageBean.getEndItemInPage());

		if (sort.equals("old")) {

			// 결과 내역 오래된 순 정렬
			generalHistorySearchResult = searchDao
					.generalSelectByDateOrderByOldList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", generalHistorySearchResult);

		} else if (sort.equals("new")) {

			// 결과 내역 최신 순 정렬
			generalHistorySearchResult = searchDao
					.generalSelectByDateOrderByNewList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", generalHistorySearchResult);

		} else if (sort.equals("name")) {

			// 결과 내역 이름 가나다 순 정렬
			generalHistorySearchResult = searchDao
					.generalSelectByDateOrderByNameList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", generalHistorySearchResult);

		}

		return map;

	}

	/**
	 * 작성자 : 최문정 내용 : 관리자 예약내역, 반복예약 내역 출력, 시작일과 종료일로 검색
	 * 
	 * @param request
	 * @param page
	 * @param sort
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public HashMap<String, Object> searchRepeatHistoryResult(
			HttpServletRequest request, int page, String sort, String sDate,
			String eDate) {

		ArrayList<HashMap<String, Object>> repeatHistorySearchResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		// sDate : 시작일, eDate : 종료일
		HashMap<String, Object> dateInfo = new HashMap<String, Object>();
		dateInfo.put("startDate", sDate);
		dateInfo.put("endDate", eDate);

		int totalCount = searchDao.rownumRepeatSelectByDate(dateInfo);
		PagingBean pageBean = new PagingBean(totalCount, page);

		// startPage : 페이지 시작 row, endPage : 페이지 종료 row
		HashMap<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("startDate", sDate);
		searchInfo.put("endDate", eDate);
		searchInfo.put("startPage", pageBean.getBeginItemInPage());
		searchInfo.put("endPage", pageBean.getEndItemInPage());

		if (sort.equals("old")) {

			// 결과 내역 오래된 순 정렬
			repeatHistorySearchResult = searchDao
					.repeatSelectByDateOrderByOldList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", repeatHistorySearchResult);

		} else if (sort.equals("new")) {

			// 결과 내역 최신순 정렬
			repeatHistorySearchResult = searchDao
					.repeatSelectByDateOrderByNewList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", repeatHistorySearchResult);

		} else if (sort.equals("name")) {

			// 결과 내역 이름 가나다 순 정렬
			repeatHistorySearchResult = searchDao
					.repeatSelectByDateOrderByNameList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", repeatHistorySearchResult);

		}

		return map;

	}

	/**
	 * 작성자 : 최문정 내용 : 관리자 예약내역 중 모달 상세내역 출력
	 * 
	 * @param repeatNo
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> showHistDetail(int repeatNo) {

		int repeatSeq = repeatNo;

		ArrayList<HashMap<String, Object>> searchDetailResult = new ArrayList<HashMap<String, Object>>();
		searchDetailResult = searchDao
				.repeatSearchDetailContentsInHistory(repeatSeq);

		return searchDetailResult;

	}

	/**
	 * 작성자 : 최문정 내용 : 관리자 예약내역, 노쇼 목록 출력, 시작일과 종료일로 검색
	 * 
	 * @param request
	 * @param page
	 * @param sort
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public HashMap<String, Object> searchNoshowHistoryResult(
			HttpServletRequest request, int page, String sort, String sDate,
			String eDate) {

		ArrayList<HashMap<String, Object>> noshowHistorySearchResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		// sDate : 시작일, eDate : 종료일
		HashMap<String, Object> dateInfo = new HashMap<String, Object>();
		dateInfo.put("startDate", sDate);
		dateInfo.put("endDate", eDate);

		int totalCount = searchDao.rownumNoshowSelectByDate(dateInfo);
		PagingBean pageBean = new PagingBean(totalCount, page);

		// startPage : 페이지 시작 row, endPage : 페이지 종료 row
		HashMap<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("startDate", sDate);
		searchInfo.put("endDate", eDate);
		searchInfo.put("startPage", pageBean.getBeginItemInPage());
		searchInfo.put("endPage", pageBean.getEndItemInPage());

		if (sort.equals("old")) {

			// 결과 내역 오래된 순 정렬
			noshowHistorySearchResult = searchDao
					.noshowSelectByDateOrderByOldList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("noshowHistorySearchResult", noshowHistorySearchResult);

		} else if (sort.equals("new")) {

			// 결과 내역 최신순 정렬
			noshowHistorySearchResult = searchDao
					.noshowSelectByDateOrderByNewList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("noshowHistorySearchResult", noshowHistorySearchResult);

		} else if (sort.equals("name")) {

			// 결과 내역 이름 가나다 순 정렬
			noshowHistorySearchResult = searchDao
					.noshowSelectByDateOrderByNameList(searchInfo);

			map.put("pageBean", pageBean);
			map.put("noshowHistorySearchResult", noshowHistorySearchResult);

		}

		return map;

	}

	// ///////////////////////////////////////10월 31이후
	// 수정//////////////////////////////////////////////////////////////////////

	/**
	 * 작성자 : 최문정 내용 : 일반 사용자 검색 결과 출력
	 * 
	 * @param request
	 * @param searchCont
	 * @param selectOpt
	 * @param page
	 * @param generalSort
	 * @return
	 */
	public HashMap<String, Object> generalUserSearchResult(
			HttpServletRequest request, String searchCont, String selectOpt,
			int page, String generalSort, String sDate, String eDate) {

		ArrayList<HashMap<String, Object>> searchResult = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> searchInfo = new HashMap<String, Object>();
		HashMap<String, Object> countForPage = new HashMap<String, Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		countForPage.put("value", searchCont);
		countForPage.put("sDate", sDate);
		countForPage.put("eDate", eDate);

		searchInfo.put("value", searchCont);
		searchInfo.put("sDate", sDate);
		searchInfo.put("eDate", eDate);

		if (request.getParameter("selectSearchOpt") != null) {

			if (selectOpt.equals("all") && generalSort.equals("new")) {

				// '전체'옵션 검색, 최신순 정렬
				int totalCount = searchDao.rownumGeneralUserSelectByAll(countForPage);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao.generalUserSelectByAllOrderByNew(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("all") && generalSort.equals("old")) {

				// '전체'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao.rownumGeneralUserSelectByAll(countForPage);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao.generalUserSelectByAllOrderByOld(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("title") && generalSort.equals("new")) {

				// '회의제목' 옵션, 최신순 정렬
				int totalCount = searchDao.rownumGeneralUserSelectByTitle(countForPage);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao.generalUserSelectByTitleOrderByNew(countForPage);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("title") && generalSort.equals("old")) {

				// '회의제목' 옵션, 오래된 순 정렬 정렬
				int totalCount = searchDao.rownumGeneralUserSelectByTitle(countForPage);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao.generalUserSelectByTitleOrderByOld(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("mem_nm") && generalSort.equals("new")) {

				// '예약자 이름' 옵션, 최신 순 정렬
				int totalCount = searchDao.rownumGeneralUserSelectByMemNM(countForPage);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao.generalUserSelectByMemNMOrderByNew(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("mem_nm") && generalSort.equals("old")) {

				// '예약자 이름' 옵션, 오래된 순 정렬
				int totalCount = searchDao.rownumGeneralUserSelectByMemNM(countForPage);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao.generalUserSelectByMemNMOrderByOld(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("mem_pn") && generalSort.equals("new")) {

				// 예약자 번호, 최신순 정렬
				int totalCount = searchDao.rownumGeneralUserSelectByMemPN(countForPage);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao.generalUserSelectByMemPNOrderByNew(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			} else if (selectOpt.equals("mem_pn") && generalSort.equals("old")) {

				// 예약자 번호, 최신순 정렬
				int totalCount = searchDao.rownumGeneralUserSelectByMemPN(countForPage);
				PagingBean pageBean = new PagingBean(totalCount, page);

				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult = searchDao.generalUserSelectByMemPNOrderByOld(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			}

		}

		return map;

	}

	/**
	 * 작성자 : 최문정 내용 : 간편검색
	 * 
	 * @param request
	 * @param easySearchCont
	 * @param easySelectOpt
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> easySearchResult(
			HttpServletRequest request, String easySearchCont,
			String easySelectOpt) {

		ArrayList<HashMap<String, Object>> searchResult = new ArrayList<HashMap<String, Object>>();

		// Info to Mapper
		HashMap<String, Object> searchInfo = new HashMap<String, Object>();

		// Info to PagingMapper
		HashMap<String, Object> easyInfo = new HashMap<String, Object>();

		// easyInfo : 페이징을 위해 검색 내용 및 오늘 날짜 보냄
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date cDate = new Date();
		String today = sdf.format(cDate);

		easyInfo.put("easySearchCont", easySearchCont);
		easyInfo.put("today", today);

		searchInfo.put("value", easySearchCont);
		searchInfo.put("today", today);

		if (request.getParameter("easySelectSearchOpt") != null) {

			if (easySelectOpt.equals("e_all")) {

				// '전체'옵션 간편검색
				searchResult = searchDao.easySearchSelectByAll(searchInfo);
			} else if (easySelectOpt.equals("e_title")) {

				// '회의제목' 옵션 간편검색
				searchResult = searchDao.easySearchSelectByTitle(searchInfo);
			} else if (easySelectOpt.equals("e_mem_nm")) {

				// '예약자 이름' 옵션 간편검색
				searchResult = searchDao.easySearchSelectByMemNM(searchInfo);
			} else if (easySelectOpt.equals("e_mem_pn")) {

				// 예약자 번호 간편검색
				searchResult = searchDao.easySearchSelectByMemPN(searchInfo);

			}

		}

		return searchResult;
	}

}