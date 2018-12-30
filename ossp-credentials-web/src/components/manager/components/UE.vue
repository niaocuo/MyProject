<template>
  <div>
    <!--editor的div为富文本的承载容器-->
    <div id="editor" style="width:100%;"></div>
  </div>
</template>
<script>
  export default {
    name: 'ue',
    props: {
      value:{
        type:String,
        default:""
      },
      config:{
        type:Object,
        default:() =>{
         return{
           serverUrl:api+"/uEditor/upload",
           initialFrameHeight: 300,
         }
        }
      }
    },
    data () {
          return {
              editor: null,
          }
    },
    mounted() {
      this.editor = window.UE.getEditor('editor',this.config);
      this.editor.addListener('ready', () => {
        this.editor.setContent(this.value)
      })
    },
    methods: {
      //获取编辑器中的内容
      getUEContent () {
        return this.editor.getContent()
      }
    },
    destroyed() {
          this.editor.destroy();
      }
  }
</script>
