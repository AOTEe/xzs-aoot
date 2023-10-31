<template>
  <div>
    <ReplyHeader></ReplyHeader>

    <ReplyBox :topicId="topicId" :topicType="1"></ReplyBox>

    <ReplyList></ReplyList>
  </div>
</template>

<script>
import ReplyHeader from "@/views/video-room/components/replyHeader";
import ReplyBox from "@/views/video-room/components/replyBox";
import ReplyList from "@/views/video-room/components/replyList";
export default {
  name: "Comment",
  components: {ReplyHeader, ReplyBox ,ReplyList},
  props :{
    topicId : null
  }
}
</script>

<style scoped>

</style>
