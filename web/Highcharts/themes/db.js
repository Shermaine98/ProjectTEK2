Highcharts.theme = {
  "colors": ["#81C1B4", "#FFFFFF", "#C23C2A", "#979797", "#FBB829"],
  "chart": {
    "backgroundColor": "#5A5D65",
    style: {
        fontFamily: 'Arial'
    }
  },
  "legend": {
    "enabled": true,
    "align": "right",
    "verticalAlign": "bottom",
    "itemStyle": {
      "color": "#C0C0C0"
    },
    "itemHoverStyle": {
      "color": "#C0C0C0"
    },
    "itemHiddenStyle": {
      "color": "#444444"
    }
  },
  "title": {
    "text": {},
    "style": {
      "color": "#E2E2E2"
    }
  },
  "tooltip": {
    "backgroundColor": "#1C242D",
    "borderColor": "#1C242D",
    "borderWidth": 1,
    "borderRadius": 0,
    "style": {
      "color": "#FFFFFF"
    }
  },
  "subtitle": {
    "style": {
      "color": "#E2E2E2"
    }
  },
  "xAxis": {
    gridLineDashStyle: 'dot',
    "gridLineColor": "#666A70",
    "gridLineWidth": 2,
    "labels": {
      "style": {
        "color": "#97989D"
      }
    },
    "lineColor": "#2E3740",
    "tickColor": "#2E3740"
    
  },
  "yAxis": {
    gridLineDashStyle: 'dot',
    "gridLineColor": "#666A70",
    "gridLineWidth": 2,
    "labels": {
      "style": {
        "color": "#97989D"
      },
      "lineColor": "#97989D",
      "tickColor": "#97989D",
      "title": {
        "style": {
          "color": "#97989D"
        },
        "text": {style:{"color": "#97989D"}}
      }
    }
  }
}

// Apply the theme
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);