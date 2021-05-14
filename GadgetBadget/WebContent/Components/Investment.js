/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "InvestmentAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onItemSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("itemid"));
 $("#iname").val($(this).closest("tr").find('td:eq(0)').text());
 $("#date").val($(this).closest("tr").find('td:eq(1)').text());
 $("#amount").val($(this).closest("tr").find('td:eq(2)').text());
 $("#description").val($(this).closest("tr").find('td:eq(3)').text());

});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "InvestmentAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("itemid"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onItemDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateItemForm()
{

// iname
if ($("#iname").val().trim() == "")
 {
 return "Insert Investment name.";
 }
// date
if ($("#date").val().trim() == "")
 {
 return "Insert date.";
 } 

// amount-------------------------------
if ($("#amount").val().trim() == "")
 {
 return "Insert amount.";
 }
// is numerical value
var tmpPrice = $("#amount").val().trim();
if (!$.isNumeric(tmpPrice))
 {
 return "Insert a numerical value for amount.";
 }
// convert to decimal price
 $("#amount").val(parseFloat(tmpPrice).toFixed(2));
// DESCRIPTION------------------------
if ($("#description").val().trim() == "")
 {
 return "Insert Description.";
 }
return true;
}

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 