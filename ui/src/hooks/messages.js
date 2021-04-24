import { useSnackbar } from "notistack";
import { useCallback } from "react";

export default function useMessage() {
  const { enqueueSnackbar } = useSnackbar();

  const displayMessage = useCallback((message, type) => {
    enqueueSnackbar(message, {
      variant: type,
      preventDuplicate: true,
      anchorOrigin: {
        vertical: "bottom",
        horizontal: "center",
      },
    });
  }, [enqueueSnackbar])

  const displayError = useCallback((errorMessage) => {
    displayMessage(errorMessage, "error");
  }, [displayMessage])

  return { displayError };
}

