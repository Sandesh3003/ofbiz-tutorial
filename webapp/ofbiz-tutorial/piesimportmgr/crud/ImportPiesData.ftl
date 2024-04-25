<div class="container">
    <div class="row">
        <div class="col align-self-center">
            <h2 class="text-center">Import PIES Data</h2>
            <form id="importForm" action="<@ofbizUrl>importPiesDataEvent</@ofbizUrl>" method="post">
                <div class="form-group">
                    <label for="filePath">File Path:</label>
                    <input type="file" class="form-control" id="filePath" name="filePath" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Import File</button>
            </form>
        </div>
    </div>
</div>