<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file='/common/taglib.jsp'%>
<!DOCTYPE html>

<script src="<c:url value='/template/ckeditor/ckeditor.js' />"></script>
<c:url var="NewApiURL" value="/api-admin-new" />
<c:url var="NewURL" value="/admin-new" />

<h1>Edit news</h1>
<hr>
<div class="row">
	<!-- edit form column -->
	<div class="col-md-9 personal-info">
		<c:if test="${not empty model.message}">
			<div class="alert alert-${model.alert}">${model.message}</div>
		</c:if>
		<form id="submitForm" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${model.id}" />
			<div class="form-group">
				<label class="col-lg-3 control-label">Title:</label>
				<div class="col-lg-8">
					<input name="title" class="form-control" value="${model.title}"
						type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Thumbnail:</label>
				<div class="col-lg-8">
					<input name="thumbnail" class="form-control"
						value="${model.thumbnail}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Short description:</label>
				<div class="col-lg-8">
					<input name="shortDescription" class="form-control"
						value="${model.shortDescription}" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Category:</label>
				<div class="col-lg-8">
					<div class="ui-select">
						<select id="categoryCode" name="categoryCode" class="form-control">
							<option value="">Choose category</option>
							<c:forEach var="category" items="${model.listCategory}">
								<c:if test="${model.categoryId == category.id}">
									<option selected="selected" value="${category.code}">${category.name}</option>
								</c:if>
								<c:if test="${model.categoryId != category.id}">
									<option value="${category.code}">${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">Content:</label>
				<div class="col-lg-8">
					<textarea class="form-control" rows="15" cols="" id="content"
						name="content">${model.content}</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label"></label>
				<div class="col-md-8">
					<c:if test="${not empty model.id}">
						<input id="btnAddOrUpdateNews" type="button"
							class="btn btn-primary" value="Save changes">
					</c:if>
					<c:if test="${empty model.id}">
						<input id="btnAddOrUpdateNews" type="button"
							class="btn btn-primary" value="Add news">
					</c:if>
				</div>
			</div>
		</form>
		<script>
			
			var editor = '';
			$( document ).ready(function() {
				editor = CKEDITOR.replace('content');
			});
		
			$("#btnAddOrUpdateNews").click(function(e) {
				e.preventDefault();
				var data = {};
				var formData = $("#submitForm").serializeArray();
				$.each(formData, function(i, v) {
					data["" + v.name + ""] = v.value;
				});
				data["content"] = editor.getData();

				var id = $("#id").val();
				if (id != "") {
					updateNew(data);
				} else {
					addNew(data);
				}
			});

			function updateNew(data) {
				$.ajax({
					url : '${NewApiURL}',
					type : 'PUT',
					contentType : 'application/json',
					data : JSON.stringify(data),
					dataType : 'json',

					success : function(result) {
						window.location.href = "${NewURL}?type=edit&id="
								+ result.id + "&message=update_news_success&alert=success";
					},

					error : function(error) {
						window.location.href = "${NewURL}?type=edit&id="
								+ result.id + "&message=update_news_fail&alert=danger";
					}
				});
			}

			function addNew(data) {
				$.ajax({
					url : '${NewApiURL}',
					type : 'POST',
					contentType : 'application/json',
					data : JSON.stringify(data),
					dataType : 'json',

					success : function(result) {
						window.location.href = "${NewURL}?type=edit&id="+ result.id +"&message=add_news_success&alert=success";
					},

					error : function(error) {
						//window.location.href = "${NewURL}?type=edit&id="+ result.id +"&message=add_news_fail&alert=danger";
					}
				});
			}
		</script>
	</div>
</div>
<hr>