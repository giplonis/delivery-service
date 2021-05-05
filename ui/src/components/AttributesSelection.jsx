
import useMessage from "../hooks/messages";
import { ATTRIBUTES } from "../config";
import React, { useEffect, useState } from "react";
import { Checkbox, FormControlLabel } from "@material-ui/core";
import Skeleton from '@material-ui/lab/Skeleton';
import axiosInstance from "../api/axiosInstance";

export default function AttributesSelection(props){
  const { displayError } = useMessage()
  const [attributes, setAttributes] = useState([])
  const [isLoading, setIsLoading] = useState(true)
  const {toggleAttribute, selectedAttributes, ...rest} = props

  useEffect(() => {
    (async function () {
      try {
        const response = await axiosInstance.get(ATTRIBUTES);
        setAttributes(response.data);
      } catch (e) {
        displayError("Failed to load attributes");
      }
      finally {
        setIsLoading(false)
      }
    })();
  }, [displayError])
  return (
    <div className="form-inner" {...rest}>
      <div className="form-header">Select Attributes</div>
      <div style={{ display: "flex", flexDirection: "column" }}>
        {isLoading
          ? Array(2)
              .fill()
              .map(() => (
                <Skeleton
                  variant="rect"
                  width={120}
                  height={20}
                  style={{ marginTop: 12 }}
                />
              ))
          : attributes.map((attribute) => (
              <FormControlLabel
                control={
                  <Checkbox
                    color="primary"
                    onClick={() => {
                      toggleAttribute(attribute);
                    }}
                    checked={selectedAttributes.some(
                      (item) => item.id === attribute.id
                    )}
                  />
                }
                label={attribute.label}
              />
            ))}
      </div>
    </div>
  );
}
