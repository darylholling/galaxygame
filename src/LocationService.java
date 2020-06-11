public class LocationService {
    int board_size = 12;

    public Location[][] initalize() {
        Location[][] gridPaneNodes = new Location[board_size][board_size];
        //        initialize playfield
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                gridPaneNodes[i][j] = new Location(i, j);
//                Rectangle tile = new Rectangle(50, 50);
//                tile.setFill(Color.TRANSPARENT);
//                tile.setStroke(Color.BLACK);
//                gp.add(tile, i, j);
            }
        }

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                if (i + 1 < board_size) {
                    gridPaneNodes[i][j].setRight(gridPaneNodes[i + 1][j]);
                }

                if (i - 1 < board_size && i - 1 > 0) {
                    gridPaneNodes[i][j].setLeft(gridPaneNodes[i - 1][j]);
                }

                if (j + 1 < board_size) {
                    gridPaneNodes[i][j].setDown(gridPaneNodes[i][j + 1]);
                }

                if (j - 1 < board_size && j - 1 > 0) {
                    gridPaneNodes[i][j].setUp(gridPaneNodes[i][j - 1]);
                }
            }
        }

        return gridPaneNodes;
    }
}
