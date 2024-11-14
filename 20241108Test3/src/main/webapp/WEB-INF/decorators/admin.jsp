<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/taglib.jsp" %>
<c:url value="/" var="URL"></c:url>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8"/>
    <title>Metronic | Admin Dashboard Template</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>
    <link href="${URL}assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${URL}assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${URL}assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${URL}assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="${URL}assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
    <link href="${URL}assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet"
          type="text/css"/>
    <link href="${URL}assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
    <link href="${URL}assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL PLUGIN STYLES -->

    <!-- BEGIN PAGE STYLES -->
    <link href="${URL}assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE STYLES -->

    <!-- BEGIN THEME STYLES -->
    <link href="${URL}assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
    <link href="${URL}assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${URL}assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${URL}assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${URL}assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>

    <style>
        /* custom.css */
        body, html {
            height: 100%;
            margin: 0;
            display: flex;
            flex-direction: column;
        }

        .page-container {
            flex: 1;
            overflow-y: auto;
        }

        .footer {
            flex-shrink: 0;
            background-color: #8d6767;
        }

    </style>
</head>

<body class="page-header-fixed page-quick-sidebar-over-content page-style-square">
    <!-- Header -->
    <%@ include file="/commons/admin/header.jsp" %>

    <!-- BEGIN CONTAINER -->
    <div class="page-container">
        <!-- Sidebar -->
        <%@ include file="/commons/admin/sidebar.jsp" %>

        <!-- Main Content -->
        <div class="page-content-wrapper">
            <div class="page-content">
                <sitemesh:write property="body"/>
            </div>
        </div>

        <!-- Quick Sidebar -->
        <%@ include file="/commons/admin/quicksidebar.jsp" %>
    </div>
    <!-- END CONTAINER -->

    <div>
        <!-- Footer -->
        <%@ include file="/commons/admin/footer.jsp" %>
    </div>


    <!-- JavaScripts -->
    <script src="${URL}assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
            type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
            type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
            type="text/javascript"></script>

    <!-- Page Level Plugins -->
    <script src="${URL}assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js"
            type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>

    <!-- Page Level Scripts -->
    <script src="${URL}assets/global/scripts/metronic.js" type="text/javascript"></script>
    <script src="${URL}assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
    <script src="${URL}assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
    <script src="${URL}assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
    <script src="${URL}assets/admin/pages/scripts/index.js" type="text/javascript"></script>
    <script src="${URL}assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>

    <script>
        jQuery(document)
            .ready(function () {
                Metronic.init();
                Layout.init();
                QuickSidebar.init();
                Demo.init();
                Index.init();
                Index.initDashboardDaterange();
                Index.initJQVMAP();
                Index.initCalendar();
                Index.initCharts();
                Index.initChat();
                Index.initMiniCharts();
                Tasks.initDashboardWidget();
            });
    </script>
</body>
</html>
