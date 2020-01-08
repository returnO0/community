/**
 * 回复提交
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment(questionId,1,content);
}
function insertComment(e) {
    var id = e.getAttribute("data");
    var content = $("#comment").val();
    comment(id,2,content);
}
/**
 * 添加评论
 * @param targetId 目标id
 * @param type 评论类型
 * @param content 评论类容
 */
function comment(targetId, type, content) {
    if (!content){
        alert("不能输入空的类容");
        return
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success:function (response) {
            if (response.code===200){
                window.location.reload();
            }else {
                alert(response.message);
            }
        },
        dataType: "json"
    });
}

/**
 * 二级评论
 * 第一次点击的时候请求后台，后面点击不发送请求
 * 添加一个selected标记
 * @param e 调用该方法的对象
 */
function collapseComments(e) {
    var id = e.getAttribute("aria-controls");
    var $comment = $("#"+id);
    var $this = $(e);
    $this.toggleClass("active");
    //如果已经查询则不再查询
    if ($comment.hasClass("selected")){
        return;
    }
    $comment.addClass("selected");
}