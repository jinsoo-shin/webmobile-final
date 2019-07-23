<template>
  <div id="chartdiv" class="mt-5 pt-5"></div>
</template>
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/material.js"></script>
<script src="https://www.amcharts.com/lib/4/lang/de_DE.js"></script>
<script src="https://www.amcharts.com/lib/4/geodata/germanyLow.js"></script>
<script>
import Repository from '@/components/Repository'
import GitlabService from '@/services/GitlabService'
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";

am4core.useTheme(am4themes_animated);

var memberT = [];

export default {
	name: 'Graph',
  props: {
    limits: {type: Number, default: 5},
    loadMore: {type: Boolean, default: false}
  },
  data() {
    return {
      repositories: [],
      members: [],
      commitlist: []
    }
  },
  components: {
    Repository,
  },
  mounted() {
    this.getGitlabCommits('6084')
  },
  methods: {
    
    async getGitlabCommits(fullName) {
      this.members = [0,0,0,0,0]
      
      var len = 1
      var page = 1
      while(len > 0)
      {
        const response = await GitlabService.getCommits(fullName, page)
        if(response.status != 200) {
          break
        }
        len = response.data.length
        this.commitlist = response.data
      for(let i=0; i<this.commitlist.length; i++){
        if(this.commitlist[i].author_name == "신진수"){
          this.members[0] += 1
        }else if(this.commitlist[i].author_name == "Lee Jiseon"){
          this.members[1] += 1
        }else if(this.commitlist[i].author_name == "ahnhyunsang"){
          this.members[2] += 1
        }else if(this.commitlist[i].author_name == "조성모"){
          this.members[3] += 1
        }else if(this.commitlist[i].author_name == "박성하"){
          this.members[4] += 1
        }else if(this.commitlist[i].author_name == "SeonghaPark"){
          this.members[4] += 1

        }
      }
      memberT = this.members
      page += 1
      }
      graphT();
    }
  }

}
function graphT(){
  am4core.ready(function() {

  am4core.useTheme(am4themes_animated);
  var chart = am4core.create("chartdiv", am4charts.XYChart);
  chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

  chart.paddingBottom = 30;

  chart.data = [{
      "name": "박성하",
      "steps": memberT[4],
      "href": "https://vignette.wikia.nocookie.net/pokemon/images/3/3f/%EC%9D%B4%EB%B8%8C%EC%9D%B4_%EA%B3%B5%EC%8B%9D_%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8.png/revision/latest?cb=20170405085011&path-prefix=ko"
  }, {
      "name": "신진수",
      "steps": memberT[0],
      "href": "https://vignette.wikia.nocookie.net/pokemon/images/f/f9/%EB%82%98%EB%AA%B0%EB%B9%BC%EB%AF%B8_%EA%B3%B5%EC%8B%9D_%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8.png/revision/latest?cb=20170803021707&path-prefix=ko"
  }, {
      "name": "안현상",
      "steps": memberT[2]+30,
      "href": "https://vignette.wikia.nocookie.net/pokemon/images/5/54/%EB%A1%B1%EC%8A%A4%ED%86%A4_%EA%B3%B5%EC%8B%9D_%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8.png/revision/latest?cb=20170405023350&path-prefix=ko"
  }, {
      "name": "이지선",
      "steps": memberT[1],
      "href": "https://vignette.wikia.nocookie.net/pokemon/images/a/a9/%EC%B9%98%EC%BD%94%EB%A6%AC%ED%83%80_%EA%B3%B5%EC%8B%9D_%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8.png/revision/latest?cb=20170406064729&path-prefix=ko"
  }, {
      "name": "조성모",
      "steps": memberT[3],
      "href": "https://vignette.wikia.nocookie.net/pokemon/images/0/0f/%EC%88%98%ED%92%80%EB%B6%80%EA%B8%B0_%EA%B3%B5%EC%8B%9D_%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8.png/revision/latest?cb=20170728000950&path-prefix=ko"
  },];

  var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
  categoryAxis.dataFields.category = "name";
  categoryAxis.renderer.grid.template.strokeOpacity = 0;
  categoryAxis.renderer.minGridDistance = 10;
  categoryAxis.renderer.labels.template.dy = 35;
  categoryAxis.renderer.tooltip.dy = 35;

  var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
  valueAxis.renderer.inside = true;
  valueAxis.renderer.labels.template.fillOpacity = 0.3;
  valueAxis.renderer.grid.template.strokeOpacity = 0;
  valueAxis.min = 0;
  valueAxis.cursorTooltipEnabled = false;
  valueAxis.renderer.baseGrid.strokeOpacity = 0;

  var series = chart.series.push(new am4charts.ColumnSeries);
  series.dataFields.valueY = "steps";
  series.dataFields.categoryX = "name";
  series.tooltipText = "{valueY.value}";
  series.tooltip.pointerOrientation = "vertical";
  series.tooltip.dy = - 6;
  series.columnsContainer.zIndex = 100;

  var columnTemplate = series.columns.template;
  columnTemplate.width = am4core.percent(50);
  columnTemplate.maxWidth = 66;
  columnTemplate.column.cornerRadius(60, 60, 10, 10);
  columnTemplate.strokeOpacity = 0;

  series.heatRules.push({ target: columnTemplate, property: "fill", dataField: "valueY", min: am4core.color("#FFD700"), max: am4core.color("#F4A460") });
  series.mainContainer.mask = undefined;

  var cursor = new am4charts.XYCursor();
  chart.cursor = cursor;
  cursor.lineX.disabled = true;
  cursor.lineY.disabled = true;
  cursor.behavior = "none";

  var bullet = columnTemplate.createChild(am4charts.CircleBullet);
  bullet.circle.radius = 30;
  bullet.valign = "bottom";
  bullet.align = "center";
  bullet.isMeasured = true;
  bullet.mouseEnabled = false;
  bullet.verticalCenter = "bottom";
  bullet.interactionsEnabled = false;

  var hoverState = bullet.states.create("hover");
  var outlineCircle = bullet.createChild(am4core.Circle);
  outlineCircle.adapter.add("radius", function (radius, target) {
      var circleBullet = target.parent;
      return circleBullet.circle.pixelRadius + 10;
  })

  var image = bullet.createChild(am4core.Image);
  image.width = 60;
  image.height = 60;
  image.horizontalCenter = "middle";
  image.verticalCenter = "middle";
  image.propertyFields.href = "href";

  image.adapter.add("mask", function (mask, target) {
      var circleBullet = target.parent;
      return circleBullet.circle;
  })

  var previousBullet;
  chart.cursor.events.on("cursorpositionchanged", function (event) {
      var dataItem = series.tooltipDataItem;

      if (dataItem.column) {
          var bullet = dataItem.column.children.getIndex(1);

          if (previousBullet && previousBullet != bullet) {
              previousBullet.isHover = false;
          }

          if (previousBullet != bullet) {

              var hs = bullet.states.getKey("hover");
              hs.properties.dy = -bullet.parent.pixelHeight + 30;
              bullet.isHover = true;

              previousBullet = bullet;
          }
      }
  })

  }); 
}

</script>

<style>
#chartdiv {
  width: 100%;
  height: 500px;
}
</style>
