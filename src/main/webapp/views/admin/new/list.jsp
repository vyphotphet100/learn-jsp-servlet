<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file='/common/taglib.jsp'%>
<!DOCTYPE html>
<c:url var="NewApiURL" value="/api-admin-new" />
<c:url var="NewURL" value="/admin-new" />
<c:if test="${not empty model.message}">
	<div class="alert alert-${model.alert}">${model.message}</div>
</c:if>
<form id="frmSubmit">
	<table class="table table-responsive" style="width: 100%">
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên bài biết</th>
				<th>Mô tả ngắn</th>
				<th>Thao tác</th>
				<th>
					<label class="form-check-label" for="check1">
			        	<input type="checkbox" id="check_all"> Check all
			      	</label>
				</th>
			</tr>
		</thead>
		<c:forEach var="news" items="${model.listResult}">
			<tbody>
				<tr>
					<td>${news.id}</td>
					<td>${news.title}</td>
					<td>${news.shortDescription}</td>
					<td>
						<a href="<c:url value="/admin-new?type=edit&id=${news.id}" />">
							<button type="button" class="btn btn-default btn-sm">
								<span class="glyphicon glyphicon-edit"></span> Edit
							</button>
						</a>
					</td>
					<td>
			        	<input type="checkbox" class="checkbox_news ${news.id}" value="${news.id}">
					</td>
				</tr>
			</tbody>
		</c:forEach>
		<tr>
			<td> </td>
			<td> </td>
			<td> </td>
			<td>
				<a href="<c:url value="/admin-new?type=edit"/>" data-toggle="tooltip" title="Add article">
					<i class="fa" style="font-size:24px">&#xf055;</i>
				</a>
				<a href="#" data-toggle="tooltip" title="Remove article">
					<i id="removeNews" class="fa fa-remove" style="font-size:25px;color:red"></i>
				</a>
			</td>
		</tr>
	</table>
	<input type="hidden" id="page" name="page" value=""> <input
		type="hidden" id="nItemPerPage" name="nItemPerPage" value="">
	<input type="hidden" id="sortName" name="sortName" value=""> <input
		type="hidden" id="sortBy" name="sortBy" value=""> 
	<input type="hidden" id="type" name="type" value="">

</form>

<ul class="pagination" id="pagination"></ul>
<script>
	$(function() {
		var checkInit = false;
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = ${model.nItemPerPage};
		
		window.pagObj = $('#pagination').twbsPagination({
			totalPages : totalPages,
			visiblePages : 3,
			startPage : currentPage,
			onPageClick : function(event, page) {
				if (checkInit) {
					$("#nItemPerPage").val(limit);
					$("#page").val(page);
					$("#sortName").val("id");
					$("#sortBy").val("desc");
					$("#type").val("list");
					$("#frmSubmit").submit();
				}
				checkInit = true;
			}
		}).on('page', function(event, page) {
			console.info(page + ' (from event listening)');
		});
	});
	
	var ids = [];
	$("#removeNews").click(function(){
		var data = {};
		data["ids"] = ids;
		deleteNews(data);
	});
	
	$("#check_all").click(function() {
		ids = [];
		
		if($(this).is(":checked")){
			$(".checkbox_news").prop('checked', false);
			$(".checkbox_news").click();
		}
		else {
			$(".checkbox_news").prop('checked', true);
			$(".checkbox_news").click();
		}
			
	});
	
	$(".checkbox_news").click(function () {
		if ($("#check_all").is(":checked") == false) {
			if ($(this).is(":checked"))
				ids.push($(this).val());
			else 
				ids.splice(ids.indexOf($(this).val()), 1);
		} else {
			if ($(this).is(":checked"))
				ids.push($(this).val());
			else {
				ids.splice(ids.indexOf($(this).val()), 1);
				$("#check_all").prop("checked", false);
			}
		}
			
	});
	
	function deleteNews(data) {
		$.ajax({
			url : "${NewApiURL}", 
			type : "DELETE", 
			contentType: "application/json", 
			data: JSON.stringify(data) , 
			dataType: "json", 
			
			success: function(result) {
				window.location.href = "${NewURL}?page=1&nItemPerPage=2&sortName=id&sortBy=desc&type=list&message=delele_news_success&alert=success";
			},
			
			error : function(error) {
				window.location.href = "${NewURL}?page=1&nItemPerPage=2&sortName=id&sortBy=desc&type=list&message=delele_news_fail&alert=danger";
			}
		});
	}
	
</script>


