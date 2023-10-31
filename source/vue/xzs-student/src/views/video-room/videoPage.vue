<template>

  <div class="video-container-v1" >
    <div class="left-container scroll-sticky" >
      <Video :videoId="videoId"></Video>
      <Comment :topicId="videoId" :topicType="1"></Comment>
    </div>
  </div>

</template>

<script>
import Video from './video'
import Comment from './components/comment'
export default {
  name: "VideoPage",
  components: {
    Comment,
    Video
  },
  data() {
    return {
      videoId : 10086
    }
  }
}
</script>

<style scoped>
.video-container-v1 {
  max-width: 2540px;
  min-width: 1080px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
  box-sizing: content-box;
  position: relative;
}
.video-container-v1 {
  width: auto;
  padding: 0 10px;
}
.video-container-v1 .left-container.scroll-sticky {
  position: sticky;
  height: fit-content;
  z-index: 1;
}

</style>
