<template>
  <div class="reply-list" >
    <div class="reply-item"  v-for="(comment ,index) in commentList">
      <div class="root-reply-container">

        <div class="root-reply-avatar">
          <div class="avatar">
            <div class="bili-avatar" style="width: 40px;height:40px;">
              <img class="bili-avatar-img bili-avatar-face bili-avatar-img-radius" data-src="//i0.hdslb.com/bfs/face/2b30726aae3726da51c054f83c9894ab5cc771c1.jpg@160w_160h_1c_1s_!web-avatar-comment.avif" alt="" src="//i0.hdslb.com/bfs/face/2b30726aae3726da51c054f83c9894ab5cc771c1.jpg@160w_160h_1c_1s_!web-avatar-comment.avif">
            </div>
          </div>
        </div>
        <div class="content-warp">
          <div class="user-info">
            <div class="user-name">{{comment.member.uname}}</div>
          </div>
          <div class="root-reply">
            <span class="reply-content-container root-reply">
              <span class="reply-content">{{comment.content.message}}</span>
            </span>

            <div class="reply-info">
              <span class="reply-time">{{comment.commentTime}}</span>
              <span class="reply-like" @click="action(comment.id,1 ,index)">
                <i :class="comment.action == -1?'svg-icon like use-color like-icon liked':'svg-icon like use-color like-icon'"  style="width: 16px; height: 16px;">
                   <svg v-show="comment.action == 1" t="1636093991833" class="icon" style="width: 16px; height: 16px;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4447" width="200" height="200"><path d="M860.032 341.12h-182.08c7.488-17.408 14.72-38.528 18.048-60.544 5.952-39.872 4.992-87.36-18.368-129.088-21.76-38.848-50.304-60.928-83.52-61.376-30.72-0.384-53.888 18.176-65.728 33.408a199.296 199.296 0 0 0-32.064 59.264l-1.92 5.184c-5.44 14.976-10.88 29.952-23.04 51.456-19.712 34.816-48.832 56.128-77.696 74.368a391.936 391.936 0 0 1-30.976 17.92v552.448a4621.952 4621.952 0 0 0 351.872-5.312c51.264-2.752 100.672-28.544 127.488-76.032 24.32-43.136 55.168-108.16 74.368-187.264 20.416-84.16 24.64-152.704 24.576-195.968-0.128-46.336-38.72-78.4-80.96-78.4z m-561.344 541.312V341.12H215.808c-59.712 0-113.408 42.048-120.896 104.32a1376 1376 0 0 0 0.64 330.368c7.36 58.688 56.128 100.032 113.024 102.848 25.024 1.28 55.552 2.56 90.112 3.712z" p-id="4448" fill="#00aeec"></path></svg>
                  <svg v-show="comment.action != 1" t="1636093575017" class="icon" style="width: 16px; height: 16px;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3323" width="200" height="200"><path d="M594.176 151.168a34.048 34.048 0 0 0-29.184 10.816c-11.264 13.184-15.872 24.064-21.504 40.064l-1.92 5.632c-5.632 16.128-12.8 36.864-27.648 63.232-25.408 44.928-50.304 74.432-86.208 97.024-23.04 14.528-43.648 26.368-65.024 32.576v419.648a4569.408 4569.408 0 0 0 339.072-4.672c38.72-2.048 72-21.12 88.96-52.032 21.504-39.36 47.168-95.744 63.552-163.008a782.72 782.72 0 0 0 22.528-163.008c0.448-16.832-13.44-32.256-35.328-32.256h-197.312a32 32 0 0 1-28.608-46.336l0.192-0.32 0.64-1.344 2.56-5.504c2.112-4.8 5.12-11.776 8.32-20.16 6.592-17.088 13.568-39.04 16.768-60.416 4.992-33.344 3.776-60.16-9.344-84.992-14.08-26.688-30.016-33.728-40.512-34.944zM691.84 341.12h149.568c52.736 0 100.864 40.192 99.328 98.048a845.888 845.888 0 0 1-24.32 176.384 742.336 742.336 0 0 1-69.632 178.56c-29.184 53.44-84.48 82.304-141.76 85.248-55.68 2.88-138.304 5.952-235.712 5.952-96 0-183.552-3.008-244.672-5.76-66.432-3.136-123.392-51.392-131.008-119.872a1380.672 1380.672 0 0 1-0.768-296.704c7.68-72.768 70.4-121.792 140.032-121.792h97.728c13.76 0 28.16-5.504 62.976-27.456 24.064-15.104 42.432-35.2 64.512-74.24 11.904-21.184 17.408-36.928 22.912-52.8l2.048-5.888c6.656-18.88 14.4-38.4 33.28-60.416a97.984 97.984 0 0 1 85.12-32.768c35.264 4.096 67.776 26.88 89.792 68.608 22.208 42.176 21.888 84.864 16 124.352a342.464 342.464 0 0 1-15.424 60.544z m-393.216 477.248V405.184H232.96c-40.448 0-72.448 27.712-76.352 64.512a1318.912 1318.912 0 0 0 0.64 282.88c3.904 34.752 32.96 61.248 70.4 62.976 20.8 0.96 44.8 1.92 71.04 2.816z" p-id="3324" fill="#9499a0"></path></svg>
                </i>
                <span >{{comment.like}}</span>
              </span>
              <span class="reply-dislike" @click="action(comment.id,-1 ,index)">
                <i :class="comment.action == -1? 'svg-icon dislike use-color dislike-icon disliked':'svg-icon dislike use-color dislike-icon'"  style="width: 16px; height: 16px;">
                <svg v-show="comment.action == - 1" t="1636094030855" class="icon" style="width: 16px; height: 16px;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5103" width="200" height="200"><path d="M860.032 693.632h-182.08c7.488 17.408 14.72 38.592 18.048 60.608 5.952 39.872 4.992 87.296-18.368 129.088-21.76 38.848-50.304 60.928-83.52 61.312-30.72 0.384-53.888-18.176-65.728-33.408a199.296 199.296 0 0 1-32.064-59.2l-1.92-5.248c-5.44-14.912-10.88-29.888-23.04-51.456-19.712-34.816-48.832-56.128-77.696-74.304a391.68 391.68 0 0 0-30.976-17.984V150.592a4617.408 4617.408 0 0 1 351.872 5.312c51.264 2.752 100.672 28.608 127.488 76.096 24.32 43.136 55.168 108.16 74.368 187.264 20.416 84.096 24.64 152.64 24.576 195.904-0.128 46.336-38.72 78.464-80.96 78.464zM298.624 152.32v541.248H215.808c-59.712 0-113.408-42.048-120.896-104.32a1376 1376 0 0 1 0.64-330.432c7.36-58.624 56.128-100.032 113.024-102.848 25.024-1.216 55.552-2.56 90.112-3.648z" p-id="5104" fill="#00aeec"></path></svg>
                <svg v-show="comment.action != -1" t="1636093677814" class="icon" style="width: 16px; height: 16px;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3933" width="200" height="200"><path d="M594.112 872.768a34.048 34.048 0 0 1-29.12-10.816c-11.264-13.248-15.872-24.064-21.504-40.064l-1.92-5.632c-5.632-16.128-12.8-36.864-27.712-63.232-25.344-44.928-50.24-74.432-86.144-97.024-23.104-14.528-43.648-26.432-65.024-32.64V203.84a4570.24 4570.24 0 0 1 339.072 4.672c38.656 2.048 72 21.12 88.896 52.032 21.504 39.36 47.232 95.744 63.552 163.008 16.448 67.52 21.568 123.776 22.592 163.008 0.448 16.832-13.44 32.256-35.392 32.256h-197.248a32 32 0 0 0-28.608 46.336l0.128 0.32 0.64 1.28 2.56 5.568c2.176 4.8 5.12 11.776 8.384 20.16 6.528 17.088 13.568 39.04 16.768 60.416 4.928 33.344 3.712 60.16-9.344 84.992-14.08 26.688-30.016 33.728-40.576 34.944z m97.728-190.016h149.568c52.8 0 100.864-40.128 99.392-97.92a846.336 846.336 0 0 0-24.32-176.448 742.016 742.016 0 0 0-69.632-178.56c-29.248-53.44-84.48-82.304-141.824-85.248-55.68-2.88-138.24-5.952-235.712-5.952-96 0-183.488 3.008-244.672 5.76-66.368 3.136-123.328 51.392-130.944 119.872a1380.608 1380.608 0 0 0-0.768 296.704c7.68 72.768 70.4 121.792 140.032 121.792h97.728c13.76 0 28.16 5.504 62.976 27.392 24.064 15.168 42.432 35.264 64.448 74.368 11.968 21.12 17.472 36.864 22.976 52.736l2.048 5.888c6.656 18.88 14.336 38.4 33.216 60.416 19.456 22.72 51.456 36.736 85.184 32.768 35.2-4.096 67.776-26.88 89.792-68.672 22.208-42.112 21.888-84.8 16-124.288a343.04 343.04 0 0 0-15.488-60.608zM298.688 205.568v413.184H232.96c-40.512 0-72.448-27.712-76.352-64.512a1318.912 1318.912 0 0 1 0.64-282.88c3.904-34.816 32.896-61.248 70.4-62.976 20.8-0.96 44.736-1.92 71.04-2.816z" p-id="3934" fill="#9499a0"></path></svg>
              </i>
              </span>
              <span class="reply-btn" @click="doReply(comment.id)">回复</span>
            </div>
          </div>
        </div>
      </div>

      <div class="sub-reply-container">
        <div class="sub-reply-list" >
          <SubReplyItem :index="index" :subIndex="subIndex" :subReply="subReply" v-for="(subReply,subIndex) in comment.subReply">
          </SubReplyItem>
          <div class="view-more" >
            <div class="view-more-default" >
              <span v-show="comment.subReplyCount>0">共{{comment.subReplyCount}}条回复 </span>
              <span class="view-more-btn" v-show="comment.subReplyCount>2" @click="moreComments(comment.id,0,index)">,点击查看</span></div>
          </div>
        </div>
      </div>
      <div class="reply-box-container" v-show="index == subReplyBoxIndex">
        <ReplyBox
          :placeholderText="subReplyPlaceholder"
          :previousCommentId="subPreviousCommentId"
          :topicId="comment.id"
          :topicType="3"
          :index = "index"
        ></ReplyBox>
      </div>

      <div class="bottom-line" ></div>
    </div>
  </div>

</template>

<script>
import SubReplyItem from "@/views/video-room/components/subReplyItem";
import ReplyBox from "@/views/video-room/components/replyBox";
import CommonApi from "@/api/commonRequest";
export default {
  name: "ReplyList",
  components : {SubReplyItem,ReplyBox},
  data() {
    return {
      videoId : "10086",
      commentList: null,
      subReplyBoxIndex : -1,
      subReplyPlaceholder : "",
      subPreviousCommentId: "",
    }
  },
  methods:{
     initComments(){
        CommonApi.postApi('/api/student/comment/getVideoComments/'+this.videoId ).then(res => {
          this.commentList = res.data
        })
    },
    showReplyBox(uname, uid, previousCommentId, index) {
      debugger
      this.subReplyBoxIndex = index;
      this.subReplyPlaceholder = "@" + uname + "：";
      this.subPreviousCommentId = previousCommentId
    },
    moreComments(rootCommentId, pageIndex, subReplyIndex) {
      var param = new FormData()
      param.append("rootCommentId", rootCommentId)
      param.append("index", pageIndex)
      CommonApi.formDataPostApi("/api/student/comment/more", param).then(res => {
        this.commentList[subReplyIndex].subReply = res.data
      })
    },
    action(commentId, action ,index) {
       debugger
      //点赞数和点赞状态变换
      if (this.commentList[index].action == action) {
        if (action == 1)
          this.commentList[index].like -= 1
        else
          this.commentList[index].dislike -= 1
        this.commentList[index].action = 0
      } else {
        if (action > 0) {
          this.commentList[index].like += action;
          if (this.commentList[index].action < 0)
            this.commentList[index].dislike += action
        } else if (action < 0) {
          this.commentList[index].dislike += action
          if (this.commentList[index].action > 0)
            this.commentList[index].like += action;
        }
        this.commentList[index].action = action
      }
      var param = new FormData()
      param.append("commentId", commentId)
      param.append("action", action)
      CommonApi.formDataPostApi("/api/student/like/commentAction", param).then(res => {
        console.log("action>>success")
      })
    }
  },

  created() {
    this.initComments()
  },
  mounted() {
    this.$bus.$on('appendReplyList', (data) => {
      this.commentList.push(data);
    });
    this.$bus.$on('subActionBack',(index,subIndex,action,like,dislike) =>{
      debugger
      this.commentList[index].subReply[subIndex].action = action
      this.commentList[index].subReply[subIndex].like += like
      this.commentList[index].subReply[subIndex].dislike += dislike
    })
  }
}
</script>

<style scoped>
.reply-list {
  margin-top: 14px;
  padding-bottom: 100px;
}
.reply-item {
  position: relative;
}
.reply-item .root-reply-container {
  padding: 22px 0 0 80px;
}
.reply-item .bottom-line {
  margin-left: 80px;
  border-bottom: 1px solid #e3e5e7;
  margin-top: 14px;
}
.reply-item .sub-reply-container {
  padding-left: 72px;
}
.sub-reply-list .view-more .view-more-default .view-more-btn{
  cursor: pointer;
}
.sub-reply-list .view-more {
  padding-left: 8px;
  font-size: 13px;
  color: #9499a0;
}
.reply-item .root-reply-container .content-warp .root-reply .reply-info {
  display: flex;
  align-items: center;
  position: relative;
  margin-top: 2px;
  font-size: 13px;
  color: #9499a0;
}
.reply-info .reply-like {
  display: flex;
  align-items: center;
  margin-right: 19px;
  cursor: pointer;
}
.reply-info .reply-dislike{
  display: flex;
  align-items: center;
  margin-right: 19px;
  cursor: pointer;
}
.reply-info .reply-btn {
  cursor: pointer;
}
.reply-info {
  display: flex;
  align-items: center;
  position: relative;
  margin-top: 2px;
  font-size: 13px;
  color: #9499a0;
}
.reply-item .root-reply-container .content-warp .root-reply {
  font-size: 16px;
  line-height: 26px;
}
.reply-time {
  margin-right: 20px;
}
.reply-item .sub-reply-container {
  padding-left: 72px;
}
.reply-item .root-reply-container .root-reply-avatar{
  display: flex;
  justify-content: center;
  position: absolute;
  left: 0;
  width: 80px;
  cursor: pointer;
}
.bili-avatar-img-radius {
  border-radius: 50%;
}
.bili-avatar-img {
  border: none;
  display: block;
  -o-object-fit: cover;
  object-fit: cover;
  /* image-rendering: -webkit-optimize-contrast; */
}
.bili-avatar-face {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%,-50%);
  -moz-transform: translate(-50%,-50%);
  -ms-transform: translate(-50%,-50%);
  -o-transform: translate(-50%,-50%);
  transform: translate(-50%,-50%);
  width: 100%;
  height: 100%;
}

.reply-item .root-reply-container .content-warp .user-info {
  font-size: 13px;
  display: flex;
  align-items: center;
  margin-bottom: 4px;
}
.reply-item .root-reply-container .content-warp .root-reply {
  font-size: 15px;
  line-height: 24px;
  position: relative;
  padding: 2px 0;
}
.reply-item .reply-box-container {
  padding: 25px 0 10px 80px;
}
.svg-icon .like .use-color .like-icon :hover, .svg-icon .like .use-color .like-icon .liked {
  color: #00aeec;
}
.svg-icon .dislike .use-color .dislike-icon :hover, .svg-icon .dislike .use-color .dislike-icon .disliked {
  color: #00aeec;
}
</style>
